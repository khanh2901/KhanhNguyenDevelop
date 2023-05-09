/*
AOS.init({
  offset:80,
  duration: 1000,
  easing: 'ease-in',
  disable:'mobile'
https://cdn.rawgit.com/michalsnik/aos/2.1.1/dist/aos.js
https://cdn.rawgit.com/michalsnik/aos/2.1.1/dist/aos.css
});*/

$(document).ready(function()
 {
  
   $('.nav a').on('click', function(){
    $('.navbar-toggle').click() 
});
  
});

$(window).scroll(function() {
  if ($(document).scrollTop() > 60) {
    $('nav').addClass('shrink');
  } else {
    $('nav').removeClass('shrink');
  }
});


var food_items = new Array(" Chicken Breast ","  Fresh Grilled Asparagus","Pork-Stuffed Skins"," Grilled Chicken"," Steak Frites");

var menus = document.querySelectorAll('.list');
for(var i=0;i< menus.length ;i++)
  {
 menus[i].innerHTML = "<li>" + food_items[0] +"<span>"+"................."+"</span>"+ "<b>"+"$19.50"+"</b>"+"</li>" 
  + "<li>" + food_items[1] +"<span>"+"......"+"</span>"+ "<b>"+"$25.00"+"</b>"+ "</li>"
  + "<li>" + food_items[2] + "<span>"+"............."+"</span>"+ "<b>"+"$25.50"+"</b>"+"</li>" 
  + "<li>" + food_items[3] + "<span>"+"................."+"</span>"+ "<b>"+"$25.49"+"</b>"+"</li>"
  + "<li>" + food_items[4] +"<span>"+"....................."+"</span>"+ "<b>"+"$25.00"+"</b>"+ "</li>" ;
  }

