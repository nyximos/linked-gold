#!/bin/bash

SERVICE_NAME=$1
PROJECT_ROOT="/home/ec2-user/linked-gold/$SERVICE_NAME"
JAR_FILE="$PROJECT_ROOT/build/libs/$SERVICE_NAME-0.0.1-SNAPSHOT.jar"

# 디렉토리가 없으면 생성
mkdir -p "$PROJECT_ROOT"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"
TIME_NOW=$(date '+%Y-%m-%d %H:%M:%S')

# 로그 파일이 존재하지 않으면 생성
if [ ! -f "$DEPLOY_LOG" ]; then
  touch "$DEPLOY_LOG"
fi

# 현재 실행 중인 애플리케이션 PID 확인 후 종료
CURRENT_PID=$(pgrep -f $JAR_FILE)

# 기존 파일 삭제
if [ -d "$PROJECT_ROOT" ]; then
  echo "$TIME_NOW > Deleting existing files in $PROJECT_ROOT" >> $DEPLOY_LOG
  rm -rf "$PROJECT_ROOT"/*
else
  echo "$TIME_NOW > Directory $PROJECT_ROOT does not exist." >> $DEPLOY_LOG
fi

if [ -z "$CURRENT_PID" ]; then
  echo "$TIME_NOW > $SERVICE_NAME is not running." >> $DEPLOY_LOG
else
  echo "$TIME_NOW > Stopping $SERVICE_NAME with PID $CURRENT_PID" >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
  echo "$TIME_NOW > $SERVICE_NAME stopped." >> $DEPLOY_LOG
fi
