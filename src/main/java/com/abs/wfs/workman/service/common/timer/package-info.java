/**
 * 타이머 서비스
 * 배치 등, 장애 이후 상태를 추적 관리 및 확인 해야하는 시기에 사용하는 서비스
 *
 * 케이스 1. CST 도착 후, TOOL COND 시, 설비 상태가 안맞아 투입안하고 대기 된 상태
 * 케이스 2. Work Order 발송 후, 설비에서 응답없는 상태가 되었을 때
 *
 *
 * 식별 후 대응 방안은
 * 1. Work 요청 후, 다음 응답이 없는지 확인
 * 2. CMR 요청 후, 응답 확인
 */
package com.abs.wfs.workman.service.common.timer;