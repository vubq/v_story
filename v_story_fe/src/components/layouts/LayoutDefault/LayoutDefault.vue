<template>
    <div class="layout_default">
        <Header />
        <div class="layout_main">
            <router-view/>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import Header from './Header.vue';

@Options({
    components: {
        Header,
    },
})
export default class LayoutDefault extends Vue {

}
</script>

<style scoped lang="scss">
.layout_default {
    .layout_main {
        padding: 0px 400px;
    }
}
</style>
