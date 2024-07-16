<template>
  <div class="modal" v-if="isVisible">
    <div class="modal-content">
      <div class="modal-header">
        <h2>User Details</h2>
        <span class="close" @click="closeModal">&times;</span>
      </div>
      <div class="modal-body">
        <div class="modal-left">
          <p><strong>ID:</strong> {{ user.userId }}</p>
          <p><strong>Name:</strong> {{ user.name }}</p>
          <p><strong>Username:</strong> {{ user.username }}</p>
          <p><strong>Email:</strong> {{ user.email }}</p>
          <p><strong>Phone:</strong> {{ user.phone }}</p>
          <p><strong>Website:</strong> {{ user.website }}</p>
          <h3 class="albums">Albums</h3>
          <ul>
            <li v-for="album in albums" :key="album.id">{{ album.title }}</li>
          </ul>
        </div>
        <div class="modal-right">
          <h3>Address</h3>
          <p><strong>Street:</strong> {{ user.addressStreet }}</p>
          <p><strong>Suite:</strong> {{ user.addressSuite }}</p>
          <p><strong>City:</strong> {{ user.addressCity }}</p>
          <p><strong>Zipcode:</strong> {{ user.addressZipcode }}</p>
          <h3 class="company">Company</h3>
          <p><strong>Name:</strong> {{ user.companyName }}</p>
          <p><strong>Catch Phrase:</strong> {{ user.companyCatchPhrase }}</p>
          <p><strong>BS:</strong> {{ user.companyBs }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserModal',
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    user: {
      type: Object,
      required: true,
      default: () => ({})
    },
    albums: {
      type: Array,
      required: true
    }
  },
  methods: {
    closeModal() {
      this.$emit('close');
    }
  }
};
</script>

<style scoped>
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  width: 60%;
  max-width: 800px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  border-radius: 10px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 97%;
}

.modal-body {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.modal-left,
.modal-right {
  width: 45%;
}

.close {
  color: #aaa;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
}

.albums {
  margin-top: 40px;
}

.company {
  margin-top: 65px;
}

ul {
  width: 300px;
}
</style>
