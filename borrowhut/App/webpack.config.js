var webpack = require('webpack');
var LiveReloadPlugin = require('webpack-livereload-plugin');

module.exports = {
    entry: [
      "./js/swiper.min.js", "./main.js", "./js/digitsCordova.js"
    ],
    output: {
      path: __dirname + '/build',
      filename: "bundle.js"
    },
    module: {
      loaders: [
        { test: /\.js?$/, loaders: ['babel'], exclude: /node_modules/ },
        { test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader', query: {compact: false}}
      ]
    },
    plugins: [
      new webpack.NoErrorsPlugin(),
      new LiveReloadPlugin()
    ]

};
