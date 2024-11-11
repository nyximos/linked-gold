#!/bin/bash

PROJECT_ROOT="/home/ec2-user/linked-gold/auth-service"
BUILD_JAR="$PROJECT_ROOT/build/libs/auth-service-0.0.1-SNAPSHOT.jar"
JAR_NAME=$(basename $BUILD_JAR)
DEPLOY_PATH="$PROJECT_ROOT/deploy"
DEPLOY_JAR=$DEPLOY_PATH/$JAR_NAME
DEPLOY_LOG="$DEPLOY_PATH/auth-deploy.log"

TIME_NOW=$(date '+%Y-%m-%d %H:%M:%S')

echo ">>> build 파일명: $JAR_NAME" >> $DEPLOY_LOG

echo "$TIME_NOW >>> build 파일 복사" >> $DEPLOY_LOG
mkdir -p $DEPLOY_PATH
cp $BUILD_JAR $DEPLOY_PATH

# 현재 구동 중인 애플리케이션 pid 확인
echo "$TIME_NOW >>> 현재 실행중인 애플리케이션 pid 확인 후 일괄 종료" >> $DEPLOY_LOG
CURRENT_PID=$(pgrep -f $DEPLOY_JAR)  # 수정된 부분

# 프로세스가 켜져 있으면 종료
if [ -z "$CURRENT_PID" ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
  echo "$TIME_NOW > 애플리케이션 종료 완료" >> $DEPLOY_LOG
fi

echo ">>> DEPLOY_JAR 배포" >> $DEPLOY_LOG
echo ">>> $DEPLOY_JAR의 $JAR_NAME를 실행합니다" >> $DEPLOY_LOG
nohup java -jar $DEPLOY_JAR >> $DEPLOY_LOG 2>> $DEPLOY_PATH/deploy_err.log &
