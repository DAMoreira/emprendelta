<!-- Basic meta tags -->
<meta charset="utf-8">
<title>Emprendelta | UTN :: Facultad Regional Delta</title>

<!-- css -->
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
<link href="/css/style.css" rel="stylesheet" media="screen">

<!--[if lt IE 7]>
    <![endif]-->

<!--[if lt IE 9]>
	   <script>
	      document.createElement('header');
	      document.createElement('nav');
	      document.createElement('section');
	      document.createElement('article');
	      document.createElement('aside');
	      document.createElement('footer');
	   </script>
	<![endif]-->

<!--[if lt IE 9]>
    	<style type="text/css">
		#page-wrapper, .sf-shadow ul {
			behavior: url(/sites/all/themes/icompany/js/PIE.htc);
		}
		
		header, nav, section, article, aside, footer {
		   display:block;
		}
		
		.sf-shadow ul {
		    box-shadow:none !important;
		}
		
		.portfolio-hover{
			display:none;
		}
		</style>
	<![endif]-->


<!-- Javascript -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<!-- jQuery from Google CDN -->
<script>
	window.jQuery
			|| document.write(
					'<script src="/js/jquery-1.8.3.min.js"><\/script>')
</script>
<!-- jQuery Local if CDN failed -->
<script src="/js/bootstrap.min.js"></script>
<!-- Bootstrap  -->
<script src="/js/modernizr.custom.js"></script>
<!--  To detact CSS3 & HTML5-->
<script type="text/javascript"
	src="/js/jquery-ui-1.8.2.custom.min.js"></script>
<!-- Jquery UI -->

<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script> 
        <![endif]-->
<!--script executions -->
<script type="text/javascript">
	//superfish initialize	
			(function($) {
				jQuery(function() {
					jQuery('ul.sf-menu').superfish();
				});

				//superfish > supersubs
				$(document).ready(function() {
					$("ul.sf-menu").superfish();

					//image preloader 
					$(function() {
						$(".portfolio-image").preloader();
						$(".fancy-preload").preloader();
						$(".node .field-type-image").preloader();
					});

					// remove main menu title if exist
					$('#wap-menu .titlecontainer').remove();

					// colorbox
					$(".portfolio-link").colorbox();

				});
			})(jQuery);

	// Tiny nav
	/*
	(function ($) {
		$(function () {
		    $("#nav").tinyNav();
		  });
	})(jQuery);
	 */

	// mobile nav
	(function($) {
		// remove tip/desc text from menu if screen is less than 979px wide
		$(document).ready(function() {
			var ic_windowWidth;
			ic_windowWidth = $(window).width();

			if (ic_windowWidth < 980) {
				$('span.tip').remove();
			}

			$(window).resize(function() {

				var ic_NewWindowWidth;
				ic_NewWindowWidth = $(window).width();

				if (ic_NewWindowWidth < 980) {
					$('span.tip').remove();
				}

				$('.selectnav').remove();
				selectnav('nav');
			})
		});
	})(jQuery);

	(function($) {
		$(function() {
			selectnav('nav');
		});
	})(jQuery);

	//back to top
	(function($) {
		$(function() {
			$(window).scroll(function() {
				if ($(this).scrollTop() != 0) {
					$('#toTop').fadeIn();
				} else {
					$('#toTop').fadeOut();
				}
			});

			$('#toTop').click(function() {
				$('body,html').animate({
					scrollTop : 0
				}, 800);
			});
		});
	})(jQuery);
</script>
