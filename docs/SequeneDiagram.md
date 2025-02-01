# Sequence Diagram
## 1. 포인트
### 1.1 포인트 충전,조회
   ```
  1) 포인트 충전
  사용자가 포인트 충전 요청 시 요청 금액만큼 포인트를 충전하여 충전 후 포인트 잔액을 반환한다.

  2) 포인트 조회
  사용자가 포인트 조회 요청 시 사용자의 포인트 잔액을 반환한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 포인트 as 포인트
      
      Note over 사용자, 포인트: 1.포인트 충전
      사용자 ->> API : 포인트 충전 요청 
      API ->> 포인트 : 포인트 충전 API 호출
      activate 포인트
      alt 충전값이 유효할 경우 
          포인트 -->> API: 충전 결과 반환
      else 충전값이 유효하지 않을 경우 
          포인트 -->> API: 에러 반환 - 입력값이 유효하지 않습니다
      end
      deactivate 포인트
      API -->> 사용자 : 수행 결과 반환

      Note over 사용자, 포인트: 2.포인트 조회
      사용자 ->> API : 잔여포인트 조회 요청
      API ->> +포인트 : 잔여포인트 조회 API 호출
      포인트 -->> -API : 잔여포인트 반환
      API -->> 사용자 : 잔여포인트 반환
  ```

## 2. 상품
### 2.1 상품 목록조회
  ```
  브랜드ID로 상품 목록을 조회한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 상품 as 상품
      
      사용자 ->> API : 상품 목록 조회 요청 with 브랜드ID
      API ->> 상품 : 상품 목록조회 API 호출
      activate 상품
      alt 브랜드ID값이 유효할 경우 
          상품 -->> API: 상품 목록 반환
      else 브랜드ID값이 유효하지 않을 경우 
          상품 -->> API: 에러 반환 - 입력값이 유효하지 않습니다
      end
      deactivate 상품
      API -->> 사용자 : 수행 결과 반환
  ```
### 2.2 상품 옵션별 재고 조회
  ```
  상품ID로 상품의 옵션별 재고를 조회한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 재고 as 재고
      
      사용자 ->> API : 상품 옵션별 재고 조회 요청 with 상품ID
      API ->> 재고 : 상품 옵션별 재고 조회 API 호출
      activate 재고
      alt 상품ID값이 유효할 경우 
          재고 -->> API: 옵션별 재고 반환
      else 상품ID값이 유효하지 않을 경우 
          재고 -->> API: 에러 반환 - 입력값이 유효하지 않습니다
      end
      deactivate 재고
      API -->> 사용자 : 수행 결과 반환
  ```
## 3. 장바구니
### 3.1 장바구니 상품 추가
  ```
  상품의 옵션 및 수량을 선택한 후 사용자의 장바구니에 상품을 추가한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 장바구니 as 장바구니
      participant 재고 as 재고

      사용자 ->> API : 장바구니 상품 추가 요청
      API ->> 장바구니 : 장바구니 상품 추가 API 호출
      activate 장바구니
      장바구니 ->> +재고 : 재고 여부 확인
      재고 -->> -장바구니 : 재고 확인
      alt 요청한 모든상품의 재고가 존재할 경우
          장바구니 -->> API: 장바구니 상품 추가 및 성공 코드 반환
      else 요청한 상품중 품절상품이 존재할 경우
          장바구니 -->> API: 에러 반환 - 품절된 상품입니다.
      end
      deactivate 장바구니
      API -->> 사용자 : 수행 결과 반환
  ```

### 3.2 장바구니 상품 목록 조회
  ```
  사용자의 장바구니 상품 목록을 조회한다. 
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 장바구니 as 장바구니
      participant 재고 as 재고
  
      사용자 ->> API : 장바구니 상품 목록 조회
      API ->> +장바구니 : 장바구니 상품 목록 조회 API 호출
      장바구니 ->> +재고 : 재고 여부 확인
      재고 -->> -장바구니 : 상품별 품절여부 확인
      장바구니 -->> -API: 상품 목록 반환
  ```

### 3.3 장바구니 상품 삭제
  ```
  사용자가 선택한 상품을 장바구니에서 삭제한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 장바구니 as 장바구니
  
      사용자 ->> API : 장바구니 상품 삭제 요청
      API ->> +장바구니 : 장바구니 상품 삭제 API 호출
      장바구니 -->> -API: 장바구니 상품 삭제 수행 코드 반환    
      API -->> 사용자 : 수행 결과 반환
  ```

## 4. 쿠폰
### 4.1 선착순 쿠폰 발급
  ```
  선착순으로 쿠폰을 발급한다. 
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 쿠폰 as 쿠폰
  
      사용자 ->> API : 선착순 쿠폰 발급 요청
      API ->> 쿠폰 : 선착순 쿠폰 발급 API 호출
      activate 쿠폰
      alt 잔여 쿠폰이 존재 하는 경우 
          쿠폰 -->> API: 쿠폰 발급    
      else 잔여 쿠폰이 존재하지 않는 경우
          쿠폰 -->> API: 에러 반환 "쿠폰이 소진되었습니다."
      end
      deactivate 쿠폰
      API -->> 사용자 : 수행 결과 반환
  ```
### 4.2 보유 쿠폰목록 조회
  ```
  사용자가 발급한 쿠폰 목록 중 사용 가능한 쿠폰 목록을 조회한다. 
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 쿠폰 as 쿠폰
      사용자 ->> API : 보유 쿠폰목록 조회 요청
      API ->> +쿠폰 : 보유 쿠폰목록 조회 API 호출
      쿠폰 -->> -API : 사용 가능한 쿠폰 목록 반환
      API -->> 사용자 : 보유 쿠폰목록 조회 요청
  ```
## 5. 주문
### 5.1 주문 요청
  ```
  선택한 상품옵션 및 수량에 대한 주문을 요청한다.
  생성된 주문은 5분내에 결제 되지 않으면 삭제한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 주문 as 주문
      participant 토큰 as 토큰
      participant 재고 as 재고

      사용자 ->> API : 주문 요청
      API ->> 주문 : 주문 요청 API 호출
      activate 주문
      주문 ->> +토큰 : 토큰 생성 요청
      토큰 ->> -주문 : 토큰 생성(TTL 설정 - 5분)
      주문 ->> 재고 : 재고 차감
      activate 재고
      재고 -->> 주문 : 재고 검증
      deactivate 재고
      alt 재고 차감이 가능한 경우
          주문 -->> 주문 : 주문 내역 생성
          주문 -->> API : 주문 내역 반환 
      else 재고 차감이 불가능한 경우
          주문 -->> API: 에러 반환 "품절된 상품이 존재합니다."
      end
      deactivate 주문
      API -->> 사용자 : 수행 결과 반환
  ```
### 5.2 인기 상품 조회
  ```
  최근 3일간의 판매량 상위 5개 품목의 목록을 조회한다. 
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 주문 as 주문

      사용자 ->> API : 인기 상품 조회 요청
      API ->> +주문 : 인기 상품 조회 API 호출
      주문 -->> 주문 : 인기상품 목록 생성 및 저장
      주문 -->> -API : 목록 반환
      API -->> 사용자 : 수행 결과 반환
  ```
## 6. 결제
### 6.1 결제 요청
  ```
  생성된 주문내역과 사용자가 선택한 적용한 쿠폰에 대해 포인트로 결제를 요청한다.
  결제 완료 후 7일이 지나면 구매확정 상태로 변경된다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 결제 as 결제
      participant 토큰 as 토큰
      participant 주문 as 주문
      participant 쿠폰 as 쿠폰
      participant 포인트 as 포인트
      participant 스케쥴러 as 스케쥴러

      사용자 ->> API : 결제 요청
      API ->> 결제 : 결제 요청 API 호출
      결제 ->> +토큰 : 토큰 검증 요청
      토큰 -->> -결제 : 토큰 검증 결과 반환
      alt 토큰이 유효한 경우
          결제 ->> +쿠폰 : 쿠폰 유효성 검증
          쿠폰 -->> -결제 : 쿠폰 유효성 검증 결과 반환
          alt 쿠폰이 유효한 경우
            결제 ->> +주문 : 주문에 대한 상품 총 금액 계산
            주문 -->> -결제 : 총 금액 반환            
            결제 ->> +쿠폰 : 쿠폰사용 및 최종 결제금액 계산
            쿠폰 -->> -결제 : 최종 결제금액 반환
            alt 사용자에게 전달받은 값 = 최종결제금액 
                결제 ->> +포인트 : 잔여포인트 조회
                alt 잔여포인트 >= 결제금액
                    포인트 -->> -결제 : 포인트 차감
                    activate 주문
                    결제 ->> 주문 : 주문상태변경
                    주문 -->> 결제 : 주문상태변경
                    deactivate 주문
                    activate 결제
                    결제 ->> 결제 : 결제내역생성(결제완료)
                    rect rgba(0, 0, 255, .1)
                        스케쥴러 ->> 스케쥴러 : 결제완료 후 7일 뒤 상태변경(구매확정)
                    end
                    deactivate 결제
                    결제 -->> API: 결제내역 반환
                else 잔여포인트 < 결제금액
                    포인트 -->> API: 에러 반환 "잔여 포인트가 부족합니다."
                end
            else 사용자에게 전달받은 값 != 최종결제금액 
                결제 -->> API: 에러 반환 "유효하지 않은 입력값 입니다."
            end
          else 쿠폰이 유효하지 경우
              쿠폰 -->> API: 에러 반환 "유효하지 않은 쿠폰사용 입니다."
          end
      else 토큰이 만료된 경우
          토큰 -->> API: 에러 반환 "만료된 주문 입니다."
      end
      API -->> 사용자 : 수행 결과 반환

  ```
### 6.2 결제 취소
  ```
  사용자가 요청한 내역에 대해 결제 취소를 진행한다.
  ```
  ```mermaid
  sequenceDiagram
      actor 사용자 as 사용자

      participant API as API
      participant 결제 as 결제
      participant 주문 as 주문
      participant 쿠폰 as 쿠폰
      participant 포인트 as 포인트

      사용자 ->> API : 결제 취소 요청
      API ->> 결제 : 결제 취소 API 호출
      결제 -->> 결제 : 결제 상태 확인
      alt 결제 상태 == 구매확정
        결제 ->> +주문 : 주문상태 변경(주문취소) 
        주문 -->> -결제 : 주문상태 변경(주문취소)
        alt 쿠폰 만료일자 <= 현재날짜
            결제 ->> +쿠폰 : 쿠폰사용취소(사용일시 삭제)
            쿠폰 -->> -결제 : 쿠폰사용취소(사용일시 삭제)
        end
        결제 ->> +포인트 : 결제 금액 반환
        포인트 -->> -결제 : 결제 금액만큼 포인트 환불
        결제 ->> 결제 : 결제상태 변경(결제취소)
      else 결제 상태 != 구매확정
        결제 -->> API : 에러 반환 "잘못된 접근입니다."
      end
      API -->> 사용자 : 수행 결과 반환
  ```
참고자료
https://mermaid.js.org/syntax/sequenceDiagram.html
https://sabarada.tistory.com/210

