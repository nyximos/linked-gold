#!/bin/bash

SERVICE_NAME=$1
PROJECT_ROOT="/home/ec2-user/linked-gold/$SERVICE_NAME"
JAR_FILE="$PROJECT_ROOT/build/libs/$SERVICE_NAME-0.0.1-SNAPSHOT.jar"

APP_LOG="$PROJECT_ROOT/$SERVICE_NAME.application.log"
ERROR_LOG="$PROJECT_ROOT/$SERVICE_NAME.error.log"
DEPLOY_LOG="$PROJECT_ROOT/$SERVICE_NAME.deploy.log"

# 디렉토리가 없으면 생성
mkdir -p "$PROJECT_ROOT"

# 서비스 디렉토리로 이동
cd "$PROJECT_ROOT"

# 로그 파일이 존재하지 않으면 생성
if [ ! -f "$DEPLOY_LOG" ]; then
  touch "$DEPLOY_LOG"
fi

TIME_NOW=$(date '+%Y-%m-%d %H:%M:%S')

# JAR 파일 실행
echo "$TIME_NOW > Starting $SERVICE_NAME..." >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$!
echo "$TIME_NOW > $SERVICE_NAME started with PID $CURRENT_PID" >> $DEPLOY_LOG
