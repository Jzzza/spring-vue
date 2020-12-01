<template>
  <v-container>
    <v-layout justify-space-around>
      <v-flex>
        <div>User profile</div>
        <v-layout row justify-space-between>
          <v-flex>
            <v-img :src="profile.userpic"></v-img>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default{
    name: 'Profile',
    computed: mapState(['profile'])
  }
</script>

<style>
</style>