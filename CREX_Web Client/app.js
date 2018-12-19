var InitDemo = function () {
  console.log("Working");

  var canvas = document.getElementById('glCanvas');
  var gl = canvas.getContext('webgl');

  if (!gl){
    console.log('WebGl not supported, falling back on experimental WebGL');
    gl = canvas.getContext('experimental-webgl');

    alert('Your browser does not support WebGL');
  }

  //canvas.width = window.innerWidth;
  //canvas.height = window.innerHeight;
  //gl.viewport(0, 0, window.innerWidth, window.innerHeight);

  //gl.clearColor(0.75, 0.85, 0.8, 1.0);
  gl.clearColor(0.0, 0.0, 0.0, 1.0);
  gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
}
