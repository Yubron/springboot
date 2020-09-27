# springboot
#### Domain , DTO 상관관계

- Domain 정의

  - 비즈니스 관점에서의 문제해결 대상

  - DB컬럼과 맵핑

  

- DTO 정의

  - Data Transfer Object
  - SVC 호출 &rarr; DAO(Repository)  호출 &rarr;  DB 결과값을 DTO에 담아 반환
  - DB컬럼과 맵핑될 수도, 되지 않을 수도 있음.



#### RequestBody / RequestParam / RequestPart

 View 단에서 Controller 단으로 Data를 전송할 때 Controller 단에서는 위와 같은 어노테이션을 통해 값을 입력받게 된다. 현재까지 공부한 내용으로 간추리는 내용이는 잘못된 부분이 있을 수 있다.

- RequestBody

  - PostsSaveRequestDto 라는 객체가 있을 경우 해당 객체에 속성을 스크립트 단에서 채워 하나의 객체로 받을 수 있다.

  - ```js
    var data = {
                title   : $('#title').val(),
                author  : $('#author').val(),
                content : $('#content').val()
               };
    
    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function() {
        alert('글이 등록되었습니다.');
        window.location.href = '/';
    }).fail(function(error){
        alert(JSON.stringify(error))
    });
    ```

  - ``` java
    @PostMapping("/posts/save")
    public static long save( @RequestBody(PostsSaveRequestDto requestDto) ) {
        postsService.save(requestDto);
    }
    
    ```

  

- RequestParam

  - 스크립트 단에서 하나하나의 파라미터를 Controller 단으로 넘겨주는 방식이다.

  - imgFile 을 업로드 하기 위해서는 formData 형식으로 넘길 수 밖에 없어 RequestBody &rarr; RequestParam을 사용했었다.

    다만 Controller 단에서의 코드가 다소 반복되는 형식을 보여 마음에 들지 않았다.

  - ```js
    var form = $('#frm-upload')[0];
    var data = new FormData(form);
    data.append("userName", $('#userName').val());
    data.append("userEmail", $('#userEmail').val());
    var imgFile = $('#imgFile').val();
    
    $.ajax({
        url: '/api/v1/posts',
        type: 'POST',
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false
    }).done(function(){
        // done action
    }).fail(function(error){
        // fail action
    });
    ```

  - ```java
    @PostMapping("/posts/save")
    public static long save( @RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("content") String content,
                             @RequestParam("imgFile") MultipartFile imgFile) {
         
        String imgFileUrl = s3Service.uploade(imgFile);		// s3 업로드 후 imgFileUrl 을 return 받아 주소를 DB에 저장
        
    	PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                                                                     .imgFileUrl(imgFileUrl)
                                                                     .title(title)
                                                                     .author(author)
                                                                     .content(content)
             														 .build();
    }
    ```

    

- RequestPart

  - 앞서 말했듯이 RequestBody 를 통해 PostsSaveRequestDto 를 js단에서 통으로 받고 싶었고 imgFile 또한 받고 싶어 구글링 중 발견한 어노테이션이다.

    MultipartFile 을 하나의 Part로, PostsSaveRequestDto를 하나의 Part로 받을 수 있어 사용해보았다.

    아직까지 단점을 찾지 못했다. 가급적 모든 경우에 RequestPart를 사용할 것 같다.





#### Springboot Batch, Scheduled 

 프로젝트 내 Posts 에는 effectiveDate(판매기간) 컬럼과 isEffective(유효성) 컬럼이 존재한다. 

effectiveDate 가 지나면 해당 Posts 는 isEffective = false 값을 갖게 되고 해당 컬럼을 통해 Post가 effective 한지 판단하게 된다. 

이러한 로직을 구현하기 위해서는 매일 밤 12시에 전체 Posts 를 조회하여 effectiveDate 를 당일 날짜와 비교하여 isEffective 값을 update 시켜주어야 한다.
