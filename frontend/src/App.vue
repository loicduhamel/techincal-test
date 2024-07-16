<template>
  <div id="app">
    <Table/>
  </div>
</template>

<script>
import Table from './components/Table.vue';

export default {
  name: 'App',
  components: {
    Table,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 20px;
}
</style>
