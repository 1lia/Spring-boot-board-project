<template>
  <div id="app">
    <div class="top">
      <b-row class="text-center mt-5">
        <b-col></b-col>
        <b-col cols="10">
          <router-view></router-view>
        </b-col>
        <b-col></b-col>
      </b-row>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
