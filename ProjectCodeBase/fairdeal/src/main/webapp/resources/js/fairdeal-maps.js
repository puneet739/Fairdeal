//This is used to store common javascript function for our application. 

var googlemapkey = 'AIzaSyCh-t_LBkS9dKDRPTkScNoHhYLLYVtsStU';
var map;

function initializeMap() {
	var mapOptions = {
		center : new google.maps.LatLng(28.4211, 77.3078),
		zoom : 13,
		minZoom : 13
	};
	map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
}

function drawMarkerList(listing) {
	for (var i = 0; i < listing.length; i++){
		drawMarker(listing[i].cordinates.latitude, listing[i].cordinates.longitude, listing[i].description, listing[i].description, map);
    }
}


function drawMarker(lat, long, name, description , map) {
	var contentOfMarker = '<div id="content"> This is the test content, Balle balle Lod of content here..asdasdasdasd  </div>';
	var infowindow = new google.maps.InfoWindow({
		content : contentOfMarker
	});

	var myLatlng = new google.maps.LatLng(lat, long);
	var marker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		title : description
	});
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
	});
}

initializeMap();
// google.maps.event.addDomListener(window, 'load', initializeMap);
