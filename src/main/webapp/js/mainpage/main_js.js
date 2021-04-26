		
	var imgtag = document.getElementById("main_img");
	var imgarr = [ "images/1.png", "images/2.png", "images/3.png" ];
	var imgindex = 0;
	function changeimg() {
		imgtag.setAttribute("src", imgarr[imgindex]);
		imgindex++;
		if (imgindex >= imgarr.length) {
			imgindex = 0;
		}
	}
	setInterval(changeimg, 5000);
	
	
	$('.script_ul > li >a').click(function(){
		$('.script_ul > li >a').attr('class','');
		
		if($(this).attr('id') == "search"){
			$('.service_search').attr('style','display:block');
			$('.Gosu_Map_Box').attr('style','display:none');
		}else if($(this).attr('id') == "gosumap"){
			$('.Gosu_Map_Box').attr('style','display:block');
			$('.service_search').attr('style','display:none');
		}
		$(this).attr('class','tab_selected');
		
	})
	
	
	$('.gosumap').click(function(){		
		$.ajax({
			
			url:"GosuMap_Ajax", 
			dataType:"JSON",
			success:function(responsedata){
				//console.log(responsedata);

				var infowindow1 = null;
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
				    mapOption = { 
				        center: new kakao.maps.LatLng(37.503250930562295, 127.02425361142784), // 처음 지도의 중심좌표
				        level: 8 // 지도의 확대 레벨
				    };

				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 사용자의 현위치 구하기 -----------------------------------------------------------------------------------------
				navigator.geolocation.getCurrentPosition(function(pos) {
				    var latitude = pos.coords.latitude;
				    var longitude = pos.coords.longitude;
				    var currentPos = new kakao.maps.LatLng(latitude,longitude);

				 	// 지도 이동
				    map.panTo(currentPos);

				    // 마커 생성
				    var marker = new kakao.maps.Marker({
				        position: currentPos
				    });

				    // 기존에 마커가 있다면 제거
				    marker.setMap(null);
				    marker.setMap(map);
				    
				 	// 인포윈도우를 생성합니다 
					infowindow1 = new kakao.maps.InfoWindow({ 
						content: '<div style="width:150px; text-align:center; padding:6px 0;">' +"현위치" + '</div>', 
						//removable : true 
					}); 
				 	
					// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
				    // 이벤트 리스너로는 클로저를 만들어 등록합니다 (for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다)
				    //kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow1));
				    //kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow1));
				 	
					infowindow1.open(map, marker); 
				});
				// -----------------------------------------------------------------------------------------------------------

				//주소를 좌표값으로 변환 후 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder(); 
					 
				$.each(responsedata,function(index, obj) {

					geocoder.addressSearch(obj.adr, function(result, status){
						if (status === daum.maps.services.Status.OK) { 
							var coords = new daum.maps.LatLng(result[0].y, result[0].x); 
							
							var marker = new daum.maps.Marker({ 
								position: coords, clickable: true }); 

							// 마커를 지도에 표시합니다. 
							marker.setMap(map); 
							
							// 인포윈도우를 생성합니다 
							var infowindow = new kakao.maps.InfoWindow({ 
								content: '<div style="width:150px; text-align:center;padding:6px 0;">' 
								         + '<a href="GosuProfile.go?email='+obj.email+'">' +obj.s_name+ '(' +obj.d_name+ ')<br><b>' +obj.name+ ' 고수</b></a></div>', 
								removable : true
							}); 
							
							// 마커에 클릭이벤트를 등록합니다 
							kakao.maps.event.addListener(marker, 'click', function() { 
							// 마커 위에 인포윈도우를 표시합니다 
							infowindow.open(map, marker); 
							}); 
						}
					});
				});

				//인포윈도우를 표시하는 클로저를 만드는 함수입니다 
					function makeOverListener(map, marker, infowindow) {
					    return function() {
					        infowindow1.open(map, marker);
					    };
					}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow1.close();
				    };
				}
			},
		  	error:function(xhr){
				   alert(xhr.status);
		  	}
		}
		
	  );
		
	});