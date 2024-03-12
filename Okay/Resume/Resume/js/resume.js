$(document).ready(function() {
    
var sideBarLink = $(".sideBarLink");
switch (sideBarLink) {
    case 0: //About
        sideBarLink[0].on("click", function() {
            $("#ABOUT").css("display", "block");
        });
    
    case 1: //Experinece
        sideBarLink[1].on("click", function() {
            $("#EXPERIENCE").css("display", "block");
        });
        break;
    default:
        break;
}
});




