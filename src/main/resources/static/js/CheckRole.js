  				var token ="eyJpZCI6MTk5LCJ1c2VyX25hbWUiOiJraGFuaG5oYW52aWVuIiwicGFzc3dvcmQiOiIkMmEkMTAkdHcxWFdsejBnSmxqRUpOamNKR2NldUxnQUZ2SjdDWGY2em5lWXJKRG5qNjltckR3QzE2UHkiLCJyb2xlIjoyfQ==";
  				console.log(token)
			  const decodedSession = JSON.parse(atob(token));
			  var userRole = decodedSession.role;
			
			  if (userRole === 2) {
			    // Lấy các phần tử cần ẩn đi
			    var element1 = document.querySelector("#nav > li:nth-child(2)");
			    var element2 = document.querySelector("#nav > li:nth-child(3)");
			    var element3 = document.querySelector("#nav > li:nth-child(8)");

			    // Kiểm tra các phần tử có tồn tại không
			    if (element1 && element2) {
			      // Ẩn các phần tử li cần ẩn đi
			      element1.style.display = "none";
			      element2.style.display = "none";
			      element3.style.display = "none";
			    }
			  }