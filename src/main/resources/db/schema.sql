USE routeedb;

-- ===========================================================
-- 기존 테이블이 있다면 초기화
-- ===========================================================
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS timeline_items;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS folder_places;
DROP TABLE IF EXISTS folders;
DROP TABLE IF EXISTS places;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;

-- ===========================================================
-- 1. 사용자 테이블
-- ===========================================================
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    nickname VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

-- ===========================================================
-- 2. 장소 캐싱 테이블
-- ===========================================================
CREATE TABLE places (
    place_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    google_place_id VARCHAR(255) NOT NULL UNIQUE,
    place_name VARCHAR(150) NOT NULL,
    address VARCHAR(255),
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    category VARCHAR(50)
);

-- ===========================================================
-- 3. 북마크 커스텀 폴더 테이블
-- ===========================================================
CREATE TABLE folders (
    folder_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    folder_name VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ===========================================================
-- 4. 폴더별 저장된 장소 매핑 테이블
-- ===========================================================
CREATE TABLE folder_places (
    folder_place_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    folder_id BIGINT NOT NULL,
    place_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (folder_id) REFERENCES folders(folder_id) ON DELETE CASCADE,
    FOREIGN KEY (place_id) REFERENCES places(place_id) ON DELETE CASCADE
);

-- ===========================================================
-- 5. 일정 메인 테이블
-- ===========================================================
CREATE TABLE schedules (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ===========================================================
-- 6. 일정별 상세 타임라인 테이블
-- ===========================================================
CREATE TABLE timeline_items (
    timeline_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL,
    place_id BIGINT NOT NULL,
    visit_order INT NOT NULL,
    visit_time TIME NOT NULL,
    visit_date DATE NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES schedules(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (place_id) REFERENCES places(place_id) ON DELETE CASCADE
);