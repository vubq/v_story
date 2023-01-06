<template>
    <div class="header">
        <div class="header_content py-2 border-b-[1px] border-gray-200 flex justify-between bg-gray-50">
            <div class="">
                <router-link to="/" class="font-bold text-xs mr-4">Thế giới truyện</router-link>
                <router-link to="/" class="font-thin text-xs mr-4">Tiên Hiệp Ký</router-link>
                <router-link to="/" class="font-thin text-xs mr-4">Ngôn Tình</router-link>
                <router-link to="/" class="font-thin text-xs">Đọc Truyện Online</router-link>
            </div>
            <div class="">
                <router-link to="/" class="font-thin text-xs mr-4">
                    <font-awesome-icon icon="fa-solid fa-mobile" />
                    Tải App Đọc Truyện
                </router-link>
                <router-link to="/" class="font-thin text-xs mr-4">
                    <font-awesome-icon icon="fa-solid fa-right-to-bracket" />
                    Đăng Nhập
                </router-link>
                <router-link to="/" class="font-thin text-xs">
                    <font-awesome-icon icon="fa-solid fa-user-plus" />
                    Đăng Ký Thành Viên
                </router-link>
            </div>
        </div>
        <div class="header_content py-4 flex justify-between items-center">
            <div>
                <img src="https://yystatic.codeprime.net/img/logos/logo-truyenyy-tet-2023.svg" class="h-[50px]" />
            </div>
            <div>
                <el-input
                    placeholder="Please input"
                    class="w-full"
                    size="large"
                    style="width: 400px;"
                >
                    <!-- <template #prepend>
                        <el-select v-model="select" placeholder="Select" style="width: 115px">
                        <el-option label="Restaurant" value="1" />
                        <el-option label="Order No." value="2" />
                        <el-option label="Tel" value="3" />
                        </el-select>
                    </template> -->
                    <template #append>
                        <!-- <el-button icon="fa-solid fa-magnifying-glass" /> -->
                    </template>
                </el-input>
            </div>
            <div>
                
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { Vue } from 'vue-class-component';

export default class Header extends Vue {

}
</script>

<style scoped lang="scss">
.header {
    .header_content {
        padding-left: 400px;
        padding-right: 400px;
    }
}
</style>
