# MovieProject (개인 사이드 프로젝트)
영화예매 프로젝트 : Java와 mysql로 구현한 영화예매 프로젝트 (프론트 X)

## 영화관 관련 초기 세팅
영화관은 하나, 영화는 여러 시간 상영 가능 ​

Ex ) 영화 A – 9:00 / 영화 B – 11:00 ​

한 id당 좌석 여러 개 예약 가능하나, 하나씩 차례대로 예약가능​

Ex ) id: asdf > 영화A- A0, A3 좌석 예약가능​

Ex ) id: abcd > 영화B-A0, 영화C-A3 좌석 예약가능​

## side Project 
- 사용 기술셋 : Java, Mysql
- ER 다이어그램 :
![image](https://user-images.githubusercontent.com/77670592/190897987-51300ad9-e659-42ae-82e3-3b86bd8d711e.png)
- 테이블 명세 : 
- ![image](https://user-images.githubusercontent.com/77670592/190898011-408f5938-44d8-4eaa-bcff-db04e2c27c72.png)
- 관계 :
- ![image](https://user-images.githubusercontent.com/77670592/190898071-733eda1c-345a-4101-b989-2ee99ee66a77.png)

<br>

1. 기능
- 회원로그인 // Admin로그인 구현 : 로그인시 public static String loginId = ""; 에 로그인 id를 담아두어 세션 구현

## 1)회원 로그인시 기능 <br>
: 1. Ticketing 2. now playing 3.Seat changing 4. Movie cancellation 5. The movie you reserved 6.Review Board 7.Member logout <br>
<h3>티켓팅, 현재 상영중 목록, 좌석변경, 영화취소, 예매한 영화, 리뷰등록, 로그아웃</h3>

## 2) Admin 로그인 기능 <br>
: 1. Add movie 2. now playing 3. (Member) Movie cancellation 4. (Member) delete account 5. Admin logout <br>
<h3>영화 추가, 현재 상영중 영화, 회원이 예매한 영화 취소, 회원 계정 삭제, 로그아웃</h3>

<br>

### 첫화면 
![image](https://user-images.githubusercontent.com/77670592/190894276-8354474f-ef8a-4043-8610-d5b2996dfc84.png)

<br>
<br>
<br>

### Member 
- 회원가입 && 로그인 구현
![MemberRegis](https://user-images.githubusercontent.com/77670592/190896362-4b95e443-f597-4f06-8347-86faa237122d.gif)

- 티켓팅 기능 && 좌석 바꾸기 구현
![Ticketing](https://user-images.githubusercontent.com/77670592/190897370-5a79df5f-3b2c-4f58-a428-3bb93b1655d4.gif)

- 티켓확인 및 취소 구현
![티켓확인과 취소](https://user-images.githubusercontent.com/77670592/190897459-72429847-7e46-456e-a662-7d12e9f05de3.gif)

- 리뷰게시판
![리뷰기능](https://user-images.githubusercontent.com/77670592/190897518-265980b8-6900-4655-b5e3-9ac2403e75a7.gif)

<br>
<br>
<br>

---


### Admin 
- 로그인, 로그아웃 구현 && 비밀번호와 일치하지 않을시 로그인 불가 설정
![adminLogin](https://user-images.githubusercontent.com/77670592/190895941-dd0c914f-d3d9-416b-b6a7-f15da071b347.gif)

- Admin기능 : 영화추가 
![AddMovie](https://user-images.githubusercontent.com/77670592/190896343-5f939739-6d4f-4b5f-a329-87ea4d7d29f5.gif)

- 회원이 예매한 영화 취소
  - 다른 무비num 선택한 경우 : 삭제안됨 
  - ![무비num없을때](https://user-images.githubusercontent.com/77670592/190897789-a48f5778-f307-4bf6-b393-08978323ee87.gif)
  - 삭제할 무비 num 일치한 경우 : 삭제됨
  - ![무비있을때](https://user-images.githubusercontent.com/77670592/190897850-cd126437-54cb-43d3-b228-882d79af8ce6.gif)


- 회원 계정 삭제
- ![계정삭제](https://user-images.githubusercontent.com/77670592/190897917-fb13e895-a716-4f5c-a329-765ce942d8b8.gif)

