#!/bin/bash

SERVICE_NAME=$1
PROJECT_ROOT="/home/ec2-user/linked-gold/$SERVICE_NAME"
JAR_FILE="$PROJECT_ROOT/build/libs/$SERVICE_NAME-0.0.1-SNAPSHOT.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date '+%Y-%m-%d %H:%M:%S')

# JAR 파일 실행
echo "$TIME_NOW > Starting $SERVICE_NAME..." >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$!
echo "$TIME_NOW > $SERVICE_NAME started with PID $CURRENT_PID" >> $DEPLOY_LOG
