function showAlert() {
    alert("The button was clicked!");
}



function loadSource(){
	       document.getElementById('formFileLg').addEventListener('change',function(){
                var file_reader=new FileReader();
                file_reader.onload=function(){
                    document.getElementById('res').textContent=file_reader.result;
                }
                file_reader.readAsText(this.files[0]);
                 alert("The button was clicked!");
            })
}
function scroll(){
		$(document).ready(function(){
		  // Add smooth scrolling to all links in navbar + footer link
		  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
		
		   // Make sure this.hash has a value before overriding default behavior
		  if (this.hash !== "") {
		
		    // Prevent default anchor click behavior
		    event.preventDefault();
		
		    // Store hash
		    var hash = this.hash;
		
		    // Using jQuery's animate() method to add smooth page scroll
		    // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
		    $('html, body').animate({
		      scrollTop: $(hash).offset().top
		    }, 900, function(){
		
		      // Add hash (#) to URL when done scrolling (default click behavior)
		      window.location.hash = hash;
		      });
		    } // End if
		  });
		})
}

function readFile(file) {
	  const reader = new FileReader();
	  reader.addEventListener('load', (event) => {
	    const result = event.target.result;
	    // Do something with result
	  });
	
	  reader.addEventListener('progress', (event) => {
	    if (event.loaded && event.total) {
	      const percent = (event.loaded / event.total) * 100;
	      console.log(`Progress: ${Math.round(percent)}`);
	    }
	  });
	  reader.readAsDataURL(file);
}

function createNewSetting(){
	const para = document.createElement("collapseThree");
	document.body.appendChild(para);

}



document.getElementById('file-selector').onchange = uploadOnChange;

      function uploadOnChange() {
          var text = this.value;
          if (this.files && this.files.length > 1) {
            var filenames = [];
            for(i=0; i++; i<this.files.length) {
              var filenames = this.files[i].name;
              var lastIndex = filename.lastIndexOf("\\");
              if (lastIndex >= 0) {
                  filename = filename.substring(lastIndex + 1);
              }
              filenames.push(filename);
            }
            text = filenames.join();
          }
          document.getElementById('filename').value = text;
      }