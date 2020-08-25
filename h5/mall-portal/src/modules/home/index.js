import React, { Component } from 'react';
import { render } from 'react-dom';
import { hashHistory } from 'react-router';
import RouteMap from './routes/index.js';

/**
 * 根挂载组件
 */
render(
	<RouteMap history={hashHistory}></RouteMap>,
  	document.getElementById('root')
)