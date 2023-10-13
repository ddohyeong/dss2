<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../css/member_info.css">
    <title>회원 정보 수정</title>
</head>
<body>

    <div class="info_wrapper">
        <div class="header">
                <img src="../logo/logo2.svg" alt="">
        </div>
        <div class="info_content">
            <form action="">
                <div class="id_box info_box">
                    <h4>
                        <label for="id">아이디</label>
                    </h4>
                    <span class="inputBox">
                        <input type="text" name="id" id="id" value="id1234" disabled="disabled">    
                    </span>
                </div>

                <div class="password_box info_box">
                    <h4>
                        <label for="password">비밀번호</label>
                    </h4>
                    <div class="pw_input_btn">
                        <span class="inputBox">
                            <input type="password" name="password" id="password" value="passowrd" disabled="disabled">    
                        </span>
    
                        <button type="button" class="pw_change_btn">비밀번호 수정</button>
                    </div>
                </div>

               
                
                <div class="name_box info_box">
                    <h4>
                        <label for="nickName">이름</label>
                    </h4>
                    <span class="inputBox">
                        <input type="text" name="name" id="name" value="홍길동">    
                    </span>
                </div>

                <div class="nickName_box info_box">
                    <h4>
                        <label for="nickName">닉네임</label>
                    </h4>
                    <span class="inputBox">
                        <input type="text" name="nickName" id="nickName" value="어쩌구">    
                    </span>
                </div>

                <div class="email_box info_box">
                    <h4>
                        <label for="email">이메일</label>
                    </h4>
                    <div class="email_input_btn">
                        <span class="inputBox">
                            <input type="text" name="email" id="email" value="a@gmail.com">    
                        </span>
    
                        <button type="button" class="send_code">인증 코드</button>
                    </div>
                    
                    <div class="code_input">
                        <span class="inputBox">
                            <input type="text" name="code" id="email">    
                        </span>
                        <button type="button">확인</button>
                    </div>

                </div>

                <div class="tel_box info_box">
                    <h4>
                        <label for="tel">연락처</label>
                    </h4>
                    
                    <div class="tel_wrap">
                        <div class="tel_front">
                            <span class="ps_box">
                                <input type="text" id="front" placeholder="010" class="int" maxlength="3">
                            </span>
                        </div>
                        <div class="tel_middle">
                            <span class="ps_box">
                                <input type="text" id="mid" placeholder="-" class="int" maxlength="4">
                                <label for="mid" class="lbl"></label>
                            </span>
                        </div>
                        <div class="tel_back">
                            <span class="ps_box">
                                <input type="text" id="back" placeholder="-" class="int" maxlength="4">
                                <label for="back" class="lbl"></label>
                            </span>
                        </div>
                    </div>
                </div>
                
                <button type="submit">회원 정보 수정</button>
            </form>
        </div>
    </div>

    


    
</body>
</html>