<template>
  <div class="table-responsive">
    <table class="table">
      <thead>
        <tr>
          <th @click="sortTable('userId')">User ID <span v-if="sortKey === 'userId'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
          <th @click="sortTable('username')">Username <span v-if="sortKey === 'username'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
          <th @click="sortTable('name')">Name <span v-if="sortKey === 'name'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
          <th @click="sortTable('email')">Email <span v-if="sortKey === 'email'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
          <th @click="sortTable('albumId')">Album ID <span v-if="sortKey === 'albumId'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
          <th @click="sortTable('albumTitle')">Album Title <span v-if="sortKey === 'albumTitle'">{{ sortOrder === 'asc' ? '▲' : '▼' }}</span></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="userAlbum in sortedUserAlbums" :key="userAlbum.albumId" @click="openModal(userAlbum)">
          <td>{{ userAlbum.userId }}</td>
          <td>{{ userAlbum.username }}</td>
          <td>{{ userAlbum.name }}</td>
          <td>{{ userAlbum.email }}</td>
          <td>{{ userAlbum.albumId }}</td>
          <td>{{ userAlbum.albumTitle }}</td>
        </tr>
      </tbody>
    </table>

    <UserModal
      v-if="showModal"
      :isVisible="showModal"
      :user="selectedUser"
      :albums="userAlbumsMap[selectedUser.username]"
      @close="closeModal"
    />
  </div>
</template>

<script>
import apiService from '../services/apiService';
import UserModal from './UserModal.vue';

export default {
  name: 'ResponsiveTable',
  components: {
    UserModal
  },
  data() {
    return {
      userAlbums: [],
      userAlbumsMap: {},
      sortKey: '',
      sortOrder: 'asc',
      showModal: false,
      selectedUser: {}
    };
  },
  computed: {
    sortedUserAlbums() {
      return [...this.userAlbums].sort((a, b) => {
        let modifier = 1;
        if (this.sortOrder === 'desc') modifier = -1;
        if (a[this.sortKey] < b[this.sortKey]) return -1 * modifier;
        if (a[this.sortKey] > b[this.sortKey]) return 1 * modifier;
        return 0;
      });
    }
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const [users, albumsByUsers, albums] = await Promise.all([
          apiService.getUsers(),
          apiService.getAlbumsByUsers(),
          apiService.getAlbums()
        ]);

        const userAlbumsData = [];
        const userAlbumsMap = {};

        users.forEach(user => {
          const userAlbums = albumsByUsers.find(au => au.username === user.username);
          if (userAlbums) {
            userAlbums.albums.forEach(albumTitle => {
              const album = albums.find(a => a.title === albumTitle && a.userId === user.id);
              if (album) {
                userAlbumsData.push({
                  userId: user.id,
                  username: user.username,
                  name: user.name,
                  email: user.email,
                  phone: user.phone,
                  website: user.website,
                  addressStreet: user.address.street,
                  addressSuite: user.address.suite,
                  addressCity: user.address.city,
                  addressZipcode: user.address.zipcode,
                  companyName: user.company.name,
                  companyCatchPhrase: user.company.catchPhrase,
                  companyBs: user.company.bs,
                  albumId: album.id,
                  albumTitle: album.title
                });
                if (!userAlbumsMap[user.username]) {
                  userAlbumsMap[user.username] = [];
                }
                userAlbumsMap[user.username].push(album);
              }
            });
          }
        });

        this.userAlbums = userAlbumsData;
        this.userAlbumsMap = userAlbumsMap;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    sortTable(column) {
      if (this.sortKey === column) {
        this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortKey = column;
        this.sortOrder = 'asc';
      }
    },
    openModal(userAlbum) {
    console.log(userAlbum);
      const user = this.userAlbums.find(u => u.userId === userAlbum.userId);
      this.selectedUser = user;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    }
  }
};
</script>

<style scoped>
.table-responsive {
  overflow-x: auto;
}

.table {
  width: 100%;
  border-collapse: collapse;
}

.table th, .table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
  cursor: pointer;
}

.table th {
  text-align: center;
}

.table th {
  background-color: #f2f2f2;
}

.table th span {
  font-size: 12px;
  margin-left: 5px;
}
</style>
