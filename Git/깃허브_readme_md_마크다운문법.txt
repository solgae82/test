1. 제목
	
	제목 블록을 지정하려면 # 후 한칸 띄움.
	ex)
	
		# 제목 크기1
		## 제목 크기2

2. -- 표시
	
	문장 후 바로 아래에 -- 또는 ** 등의 표시를 하면 그 문장의 강조 표시가 된다.
	ex)
		문장 
		--
	
	문장 후 한칸 띄우고 -- 또는 ** 등의 표시를 하면 한칸 가로 줄이 생긴다.
	ex)
		문장

		--	
	
3. 목록
	
	번호. 내용 : 번호. 후 한칸 띄움. 순서 있는 목록.
	한칸 안 띄우면 가로로 나열 됨.
	ex)
		1. 목록 
		2. 목록
			
	
	- , + , * 내용 : - 후 한칸 띄움. 순서 없는 목록 
	ex)

		- 목록
		- 목록

4. 텍스트 강조
	
	**, __ : 이 표시로 단어를 감싸면 강조 표시가 된다.
	ex)

		**강조**
		__강조__

5. 인용문 표시
	
	> , >> 내용 : 인용문 표시, > 갯수별로 들여쓰기 됨.
	ex)
		> 인용문
		>> 인용문

6. 소스코드 표시

	`소스코드`: 소스코드를 표시한다.
	ex)
		소스코드 `printf('hello world);` 입니다.


7. 링크표시

	텍스트 링크를 표시한다.
	ex)
	<http://www.daum.net>
		=> http://www.daum.net 
		(링크된 주소가 나온다)
	[다음](http://www.daum.net)
		=> 다음
		(url로 링크된 '다음' 링크가 생긴다)
	[다음](http://www.daum.net, "검색")
		=> 다음
		(url로 링크된 '다음' 링크가 생긴다.링크에 마우스 오버하면, '검색'이라는 말풍선이 뜬다.
		"검색" <- 이렇게 쌍따옴표를 써야한다. 홑따옴표(x) )

8. 이미지
	![대체텍스트](이미지url)
	ex)	
		![푸른하늘](http://www.daum.net/bluesky.png)

	깃 저장소 이미지 이용하기
	images 디력토리를 업로드 한 후 (그 디렉토리 안에 bluesky.png 가 있다)
	ex)
		![푸른하늘](./images/bluesky.png)