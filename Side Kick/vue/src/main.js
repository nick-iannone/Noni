import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import JwPagination from 'jw-vue-pagination'
import vueFlip from 'vue-flip'
import VModal from 'vue-js-modal'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSpinner, faTrashAlt, faCog, faCaretDown } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faFontAwesome } from '@fortawesome/free-brands-svg-icons'
import VueSweetalert from 'vue-sweetalert'
import VueMq from 'vue-mq'


library.add(faSpinner, faTrashAlt, faFontAwesome, faCog, faCaretDown)

Vue.config.productionTip = false

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('jw-pagination', JwPagination);
Vue.component('vue-flip', vueFlip);

Vue.use(VModal);
Vue.use(VueSweetalert);
Vue.use(VueMq, {
  breakpoints: {
    mobile: 425,
    tablet: 900,
    laptop: 1370,
    desktop: Infinity,
  }
})

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
