// JavaScript Document

jQuery(document).ready(function(){
	
	$('.navi>li').mouseover(function(){
		$(this).find('.submenu').stop().slideDown();
	}).mouseout(function(){
		$(this).find('.submenu').stop().slideUp();
	});
	
	
	$(".parther li:first,.parther li:last").click(function(){
		$("#modal").addClass("active");
	});
	
	$(".btn").click(function(){
		$("#modal").removeClass("active");
	});
	
});

$( ".btn_slides_next" ).click(function() {
    if(!$("#slides li").last().is(":visible")){
        $("#slides li:visible").hide().next("li").fadeIn("40");
        $(".btn_slides_prev").removeClass("off");
    }
    if($("#slides li").last().is(":visible")){
        $('.btn_slides_next').addClass('off');
    }
    return false;
});

$( ".btn_slides_prev" ).click(function() {
    if(!$("#slides li").first().is(":visible")){
        $("#slides li:visible").hide().prev("li").fadeIn("40");
        $(".btn_slides_next").removeClass("off");
    }
    if($("#slides li").first().is(":visible")){
        $('.btn_slides_prev').addClass('off');
    }
    return false;
});

//$( ".btn_slides_next" ).click(function() {
//  다음 버튼을 클릭하면 함수를 실행하라

//    if(!$("#slides li").last().is(":visible")){
//      현재 보여지는 이미지가 마지막 이미지가 아닐때만(!$) 즉, 첫번째랑 두번째 이미지를 말함.

//        $("#slides li:visible").hide().next("li").fadeIn("40");
//          현재 보이는 이미지를(li요소) 숨기고 다음 이미지를 0.04초 동안 서서히 보이게 하라. 

//        $(".btn_slides_prev").removeClass("off");
//          또한 이전 버튼 상태의 off를 제거하라, 즉 이전 버튼을 활성화 하라.
//    }
         
//    if($("#slides li").last().is(":visible")){
//        $('.btn_slides_next').addClass('off');
//    }
//        또한 현재 보여지는 이미지가 마지막 이미지 일때만 다음 버튼의 off를 추가하라. 
//        즉, 다음 버튼을 비활성화 하라 .

//    return false;
//});
//
//$( ".btn_slides_prev" ).click(function() {
//      이전 버튼을 클릭하면 함수룰 실행하라 

//    if(!$("#slides li").first().is(":visible")){
//     현재 보여지는 이미지가 첫번째 이미지가 아닐때만(!$) 즉, 2번째랑 3번째 이미지를 말함.

//        $("#slides li:visible").hide().prev("li").fadeIn("40");
//         현재 보이는 이미지를(li요소) 숨기고 이전 이미지를 0.04초 동안 서서히 보이게 하라. 

//        $(".btn_slides_next").removeClass("off");
//    }
//         또한 다음 버튼 상태의 off를 제거하라, 즉 다음 버튼을 활성화 하라 .

//    if($("#slides li").first().is(":visible")){
//        $('.btn_slides_prev').addClass('off');
//    }
      
//      또한 현재 보여지는 이미지가 첫번째 이미지 일때만 이전 버튼의 off를 추가하라. 
//       즉, 이전 버튼을 비활성화 하라 .

//    return false;
//});
$(".notice li:first").click(function(){
	$("#modal").addClass("active");
  });
  $(".btn").click(function(){
	$("#modal").removeClass("active");
  });






40
// Modal을 가져옵니다.
var modals = document.getElementsByClassName("modal");
// Modal을 띄우는 클래스 이름을 가져옵니다.
var btns = document.getElementsByClassName("btn");
// Modal을 닫는 close 클래스를 가져옵니다.
var spanes = document.getElementsByClassName("close");
var funcs = [];
 
// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
function Modal(num) {
  return function(){
    // 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
    btns[num].onclick =  function() {
        modals[num].style.display = "block";
        console.log(num);
    };
 
    // <span> 태그(X 버튼)를 클릭하면 Modal이 닫습니다.
    spanes[num].onclick = function() {
        modals[num].style.display = "none";
    };
  };
}
 
// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의합니다.
for(var i = 0; i < btns.length; i++) {
  funcs[i] = Modal(i);
}
 
// 원하는 Modal 수만큼 funcs 함수를 호출합니다.
for(var j = 0; j < btns.length; j++) {
  funcs[j]();
}
 
// Modal 영역 밖을 클릭하면 Modal을 닫습니다.
window.onclick = function(event) {
  if (event.target.className == "modal") {
      event.target.style.display = "none";
  }
};
Colored
