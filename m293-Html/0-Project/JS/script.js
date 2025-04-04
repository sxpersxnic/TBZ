function toggleSidebar() {
  var sidebar = document.getElementById("sidebar");
  sidebar.style.width = sidebar.style.width === "250px" ? "0" : "250px";

  var icon = document.querySelector('.menu-bars');
  icon.classList.toggle("change");
}

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
      document.getElementById("header").style.top = "0";
  } else {
      document.getElementById("header").style.top = "0";
  }
}

function search() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("mySearch");
  filter = input.value.toUpperCase();
  ul = document.getElementById("myMenu");
  li = ul.getElementsByTagName("li");

  for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
          li[i].style.display = "";
      } else {
          li[i].style.display = "none";
      }
  }
}

function toggleSearch() {
  const searchBar = document.getElementById('search-bar');
  searchBar.classList.toggle('active');
  if (searchBar.classList.contains('active')) {
      searchBar.focus(); 
  }
}
