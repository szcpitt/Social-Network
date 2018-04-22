var num=document.getElementById("blogListSize").textContent;

// Change like button to liked
for(var i=1;i<=num;i++) {
    var p = document.getElementById("checkAdded"+i);
    var status = p.textContent;
    var form = document.getElementById("likeForm"+i);
    var newButton = document.getElementById("afterClickLike"+i);
    if (status == "added") {
        form.style.display = "none";
        newButton.style.display = "inline-block";
    }
}

