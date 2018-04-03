$(document).ready(function() {
  $("a").on("click touchend", function(e) {
    var el = $(this);
    var link = el.attr("href");
    window.location = link;
  });
});


$("path, circle").mouseover(function(e) {
  $('#info-box').css('display','block');
  $('#info-box').html('<div>' + $(this).data('info') + '</div>');
});

$("path, circle").mouseleave(function(e) {
  $('#info-box').css('display','none');
});
$("#us-map").mousemove(function(e) {
  $('#info-box').css('top',e.pageY-$('#info-box').height()-30);
  $('#info-box').css('left',e.pageX-($('#info-box').width())/2);
});
/*.mouseover() (was before semicolon above)*/

/* maybe fix for mobile touch?

$('#us-map').on('touchmove',function(e){
      e.preventDefault();
});

*/

