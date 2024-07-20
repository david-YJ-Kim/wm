#!/bin/bash

# 입력된 파일명 파라미터를 변수에 저장
filename=$1

# 현재 날짜와 시간을 변수에 저장 (형식: YYYYMMDD_HHMMSS)
timestamp=$(date +"%Y%m%d_%H:%M:%S")

# 파일명과 확장자를 분리
basename="${filename%.*}"
extension="${filename##*.}"

# 새로운 파일명 생성
if [ "$extension" = "$filename" ]; then
    # 확장자가 없는 경우
    new_filename="${basename}_$timestamp"
else
    # 확장자가 있는 경우
    new_filename="${basename}_$timestamp.${extension}"
fi

# 파일명 변경 (mv 명령어 사용)
mv "$filename" "$new_filename"

# 결과 출력
echo "File renamed to: $new_filename"