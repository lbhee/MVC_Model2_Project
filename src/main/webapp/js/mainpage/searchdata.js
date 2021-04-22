var searchservice = {
	"data": 
	[
		{"name": "프로그래밍 / 코딩 레슨" , "keyword":["프로그래밍", "코딩", "자바", "자바스크립트", "html", "css"], "link" : "searchgosu.jsp", "value": 100},
		{"name": "보컬 / 발성 레슨", "keyword": ["노래", "보컬", "발성"], "link" : "searchgosu.jsp" , "value": 200},
		{"name": "퍼스널트레이닝(PT)", "keyword": ["헬스", "PT", "운동", "다이어트"], "link" : "searchgosu.jsp", "value": 300},
		{"name": "영어 / 토익 과외", "keyword": ["영어", "토익", "스피킹", "영작"], "link" : "searchgosu.jsp", "value": 400},
		{"name": "댄스 레슨", "keyword": ["스트릿", "폴", "댄스", "춤"], "link" : "searchgosu.jsp", "value": 500},
		{"name": "요리 / 조리 레슨", "keyword": ["요리", "조리", "한식", "중식", "일식", "양식"], "link" : "searchgosu.jsp", "value": 600}
	]
}

var searchbar = document.getElementById("search"); //검색어 입력
var searchlist = document.getElementById("resultmove"); //검색된 분야이동
var searchresult = document.getElementById("searchresult"); //검색결과
var searchvalue = document.getElementById("searchvalue"); //고수분야


$(function() {
	searchdata();
});


function searchdata() {
	$('#search').keyup(function() {
		for(i = 0; i < searchservice.data.length; i++) {
			if(searchservice.data[i].keyword.indexOf(searchbar.value) > -1 ) {
				$('#resultmove').empty();	
				$('#resultmove').append(searchservice.data[i].name);
				searchlist.setAttribute("href" , searchservice.data[i].link);
				searchvalue.value = searchservice.data[i].value;
				$('#resultresearch').empty();
				$('#resultresearch').append(" 🔍 " + searchbar.value + " 검색 결과");
				localStorage.setItem("고수분야",searchservice.data[i].name);
				serchvalue = searchservice.data[i].value;
			}
		    if(searchbar.value == "") {
				$('#resultmove').empty();
				$('#resultresearch').empty();
				
				
			}		
		}
	});
};
