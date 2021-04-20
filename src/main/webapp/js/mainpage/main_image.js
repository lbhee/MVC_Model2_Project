var imgtag = document.getElementById("main_img");
var imgarr = ["images/1.png", "images/2.png", "images/3.png"];
var imgindex = 0;

function changeimg() {
	imgtag.setAttribute("src", imgarr[imgindex]);
	imgindex++;
	if(imgindex >= imgarr.length) {
		imgindex = 0;
	}
} 

setInterval(changeimg, 5000);