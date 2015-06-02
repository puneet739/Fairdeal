//This file will have all the js required in application.
$(document).ready(function() {

	$('.contact__agent__button').click(function() {
		$('.modal__content').bPopup();
	});

	$('#login-link').click(function() {
		showLoginPopup();
	});

	$(".dropdown").hover(function() {
		$('.dropdown-menu', this).stop(true, true).fadeIn("fast");
		$(this).toggleClass('open');
		$('b', this).toggleClass("caret caret-up");
	}, function() {
		$('.dropdown-menu', this).stop(true, true).fadeOut("fast");
		$(this).toggleClass('open');
		$('b', this).toggleClass("caret caret-up");
	});
	
	// ga('create', 'UA-62574141-1', 'auto');
	// ga('send', 'pageview');

});

function classifiedRegisterSuccessfully() {
	alert('Thank you for registering, Classified is registered Successfully');
}

function outOfQuotaPopup() {
	alert('Your Quota is expired, Kindly get it recharged if you want to post classified');
}

function showLoginPopup() {
	$('.login_block').bPopup();
}

function showProjectPage(currentPageNumber) {
	var page = "#page-" + currentPageNumber;
	console.log("The Page number is " + currentPageNumber)
	var input = document.getElementById('pageNumber');
	input.value = currentPageNumber;
	$("#actionButton").click();
	scrollToTop(1000);
}

function clearcontactUsForm() {
	alert('Thank you for contacting Us, We will get back to you shortly.');
	$('#reset').click();
	console.log('Test');
}

function scrollToTop(scrollDuration) {
	var scrollStep = -window.scrollY / (scrollDuration / 15), scrollInterval = setInterval(
			function() {
				if (window.scrollY != 0) {
					window.scrollBy(0, scrollStep);
				} else
					clearInterval(scrollInterval);
			}, 15);
}

function scrollToDiv(divId) {
	$('html, body').animate({
		scrollTop : $(divId).offset().top
	}, 'slow');
}

(function(i, s, o, g, r, a, m) {
	i['GoogleAnalyticsObject'] = r;
	i[r] = i[r] || function() {
		(i[r].q = i[r].q || []).push(arguments)
	}, i[r].l = 1 * new Date();
	a = s.createElement(o), m = s.getElementsByTagName(o)[0];
	a.async = 1;
	a.src = g;
	m.parentNode.insertBefore(a, m)
})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
