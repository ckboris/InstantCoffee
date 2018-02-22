$("#bean-btn").click(function() {
    $("div.bean-div").show(); 
    $("div.service-div").hide();
    $("div.xhtml-div").hide();
    return false;
});

$("#service-btn").click(function() {
    $("div.service-div").show();
    $("div.bean-div").hide();
    $("div.xhtml-div").hide();
    return false;
});

$("#xhtml-btn").click(function() {
    $("div.xhtml-div").show();
    $("div.bean-div").hide();
    $("div.service-div").hide();
    return false;
});