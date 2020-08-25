const path = require('path');
const webpack = require("webpack");
let config = require('./webpack.common.js');

config = Object.assign(config, {
  
  devtool: '#eval-source-map', // for debug es6 source
  // devtool: '#cheap-module-eval-source-map',
  devServer: {
    contentBase: path.join(__dirname, "dist"),
    // compress: true,
    port: 4001,
    host: 'localhost',
    //解决版本升级问题
    //disableHostCheck:true,
    overlay: {
      warnings: true,
      errors: true
    }
  },
  plugins: [
    ...config.plugins,
    new webpack.DefinePlugin({
	    'process.env': {
	      NODE_ENV: '"dev"'
	    }
  	}),
    new webpack.HotModuleReplacementPlugin(),
  ]
});

module.exports = config
