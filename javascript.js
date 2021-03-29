function loadDoc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
		myFunction(this);
	  }
	};
	xhttp.open("GET", "examData.xml", true);
	xhttp.send();
}

function myFunction(xml){
	var i;
	var xmlDoc = xml.responseXML;
	var table="<tr><th>Date</th><th>Type of exam</th><th>Course</th><th>Semester</th><th>Students</th><th>Form of exam</th><th>Room</th></tr>";
	var x = xmlDoc.getElementsByTagName("exam");
	for (i = 0; i <x.length; i++) { 
		table += "<tr><td>" +
		x[i].getElementsByTagName("date")[0].childNodes[0].nodeValue +
		"</td><td>" +
		x[i].getElementsByTagName("type")[0].childNodes[0].nodeValue +
		"</td><td>" + 
		x[i].getElementsByTagName("course")[0].childNodes[0].nodeValue +
		"</td><td>" + 
		x[i].getElementsByTagName("semester")[0].childNodes[0].nodeValue +
		"</td><td>" +
		x[i].getElementsByTagName("students")[0].childNodes[0].nodeValue +
		"</td><td>" +
		x[i].getElementsByTagName("form")[0].childNodes[0].nodeValue +
		"</td><td>" +
		x[i].getElementsByTagName("room")[0].childNodes[0].nodeValue +
		"</td></tr>";
		
		
	  }
	  document.getElementById("demo").innerHTML = table;
}
