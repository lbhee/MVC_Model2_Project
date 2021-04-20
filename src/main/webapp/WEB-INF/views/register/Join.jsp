<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
table {
   border: solid 2px black;
   border-collapse: collapse;
}

tr {
   border: solid 10px #80F5FF;
   background-color: white;
   color: black;
}

td {
   border: solid 10px #80F5FF;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
   $(function(){
      $('#join').submit(function(){
      if($('#email').val()==""){//아이디 검사
         alert('E-MAIL을 입력해 주세요.');
         $('#email').focus();
      return false;
      
      }else if($('#pwd').val()==""){//비밀번호 검사
         alert('PWD를 입력해주세요');
            $('#pwd').focus();
         return false;
         
      }else if($('#name').val()==""){//이름 검사
          alert('이름을 입력해주세요');
            $('#name').focus();
         return false;
         
      }else if($('#adr').val()==""){//주소 검사
          alert('주소를 입력해주세요');
            $('#adr').focus();
         return false;
      }
      });
   });
</script>
</head>
<body>
   <table
      style = "width:950px; height:600px; margin-left: auto; margin-right: auto;">
      <td style="width: 700px">
      
      
      
         <form action="JoinOk.go" method="post" name="join" id="join">
            <h3 style="text-align: center;">어서와~숨고야~</h3>
            <div>
               <table
                  style="width: 400px; height: 200px; margin-left: auto; margin-right: auto;">
                  <tr>
                     <th>이름:</th>
                     <td><input type="text" name="name" id="name"></td>
                  </tr>
                  <tr>
                     <th>E-MAIL:</th>
                     <td><input type="text" name="email" id="email"></td>
                  </tr>
                  <tr>
                     <th>PWD:</th>
                     <td><input type="password" name="pwd" id="pwd"></td>
                  </tr>
                  <tr>
                     <th>주소:</th>
                     <td><input type="text" name="adr" id="adr"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <input type="submit" value="회원가입">
                        <input type="reset" value="취소">
                     </td>
                  </tr>
                  </table>
            </div>
         </form>
         
         
      </td>   
   </table>
</body>
</html>