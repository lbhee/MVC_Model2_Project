var searchservice = {
	"data": 
	[
		{"name": "프로그래밍 / 코딩 레슨" , "keyword":["코", "프로그래밍", "코딩", "자바", "자바스크립트", "html", "css"], "link" : "searchgosu.jsp"},
		{"name": "보컬 / 발성 레슨", "keyword": ["노", "음", "노래", "보컬", "발성", "음악"], "link" : "searchgosu.jsp"},
		{"name": "퍼스널트레이닝(PT)", "keyword": ["운", "헬스", "PT", "운동", "다이어트", "피티"], "link" : "searchgosu.jsp"},
		{"name": "요리 / 조리 레슨", "keyword": ["요", "요리", "조리", "한식", "중식", "일식", "양식"], "link" : "searchgosu.jsp"},
		{"name": "댄스 레슨", "keyword": ["댄", "스트릿", "폴", "댄스", "춤"], "link" : "searchgosu.jsp"},
		{"name": "영어 / 토익 과외", "keyword": ["영","영어", "토익", "스피킹", "영작"], "link" : "searchgosu.jsp"}
	]
}

var searchbar = document.getElementById("search"); //검색어 입력
var searchlist = document.getElementById("resultmove"); //검색된 분야이동
var searchresult = document.getElementById("searchresult"); //검색결과


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
				$('#resultresearch').empty();
				$('#resultresearch').append(" 🔍 <strong class='searchbar'>&#34;" + searchbar.value + "&#34;</strong> 검색 결과");
				$('.searchBox').attr('style','');
			}
		    if(searchbar.value == "") {
				$('#resultmove').empty();
				$('#resultresearch').empty();
				$('.searchBox').attr('style','display:none');
			}		
		}
	});
};