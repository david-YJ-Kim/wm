/**
 * FAR (Flow Auto Recovery)
 * 자동화 Flow 진행 중 발생한 Set-Back (차질)에 대한 자동 복구 서비스
 *
 * ApResponseIvo WFS로 Set-Back 복구 요청에 대한 응답을 줄것
 * errorCode
 *  0: AR 성공
 *  그 외 ERROR 코드 : 실패
 */
package com.abs.wfs.workman.service.flow.far;