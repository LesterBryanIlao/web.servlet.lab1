<html>
<head>
<link rel="stylesheet" href="./static/css/bootstrap.min.css">
<link rel="stylesheet" href="./static/css/mdb.min.css">
<link rel="stylesheet" href="./static/css/site.css">

<script type="text/javascript" src="./static/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="./static/js/mdb.min.js"></script>

<title>Webshoppe Online Shop</title>
</head>
<body>
	<header>

		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="#">...</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home<span
							class="sr-only">(current)</span></a></li>
				</ul>
			</div>

		</nav>
	</header>

	<!-- Begin page content -->
	<main role="main" class="container">
		<h2 class="mt-5">Search Toy</h2>
		<div>
			<a href="./catalog?category=toys">Show Toys</a> <a
				href="./catalog?category=books">Show Books</a> <a
				href="./catalog?category=flowers">Show Flowers</a>
		</div>


		<form action="./catalog" method="POST" class="needs-validation"
			novalidate>

			<div class="form-group">
				<label for="category">Category</label>
				<select name="category">
					<option value="toys">Toys</option>
					<option value="books">Books</option>
					<option value="flowers">Flowers</option>
				</select>
			</div>

			<div class="form-group">
				<label for="minimum-price">Minimum Price</label> <input
					type="number" class="form-control" id="minimum-price"
					name="minimum-price" placeholder="e.g. 100" required>
				<div class="invalid-feedback">This field is required</div>
			</div>

			<div class="form-group">
				<label for="maximum-price">Maximum Price</label> <input
					type="number" class="form-control" id="maximum-price"
					name="maximum-price" placeholder="e.g. 200" required>
				<div class="invalid-feedback">This field is required</div>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</main>

	<footer class="footer">
		<div class="container">
			<span class="text-muted">Place sticky footer content here.</span>
		</div>
	</footer>
	<script type="text/javascript">
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script>
</body>
</html>
