<template>
  <div class="board-list">
    <h2>게시판 목록</h2>
    <div>총 게시글 개수 : {{ counts.boardCount }} 총 댓글 개수 : {{ counts.commentCount }}</div>
    <div class="search-form d-flex justify-content-between align-items-center mb-3">
      <b-form-input v-model="word" placeholder="검색어를 입력하세요"></b-form-input>
      <b-form-select v-model="type">
        <option value="제목">제목</option>
        <option value="작성자">작성자</option>
        <option value="해시태그">해시태그</option>
        <option value="내용">내용</option>
      </b-form-select>
      <b-button @click="searchBoardList" variant="primary">검색 </b-button>
    </div>

    <b-table :items="boards" :fields="fields" :hover="true" @row-clicked="moveDetail"> </b-table>

    <div class="d-flex justify-content-end">
      <b-button id="write-btn" @click="moveWrite">글쓰기</b-button>
    </div>

    <div class="d-flex justify-content-center">
      <b-button :disabled="page === 0" @click="prevPage">이전</b-button>
      <b-button v-for="pageNum in 5" :key="pageNum" @click="getBoardList(page * 5 + pageNum - 1)">
        {{ page * 5 + pageNum }}
      </b-button>
      <b-button :disabled="page === maxPage" @click="nextPage">다음</b-button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  name: "BoardList",
  data() {
    return {
      pageNumber: 1,
      maxPage: 1,
      word: "",
      type: "제목",
      page: 0,
      boards: [],
      counts: {},
      fields: [
        { key: "title", label: "제목" },
        { key: "name", label: "작성자" },
        { key: "commentCount", label: "댓글 수" },
        { key: "likeCount", label: "좋아요 수" },
        { key: "hit", label: "조회 수" },
        { key: "createTime", label: "작성 시간" },
      ],
    };
  },

  created() {
    this.getBoardList(0);
    this.getCount(this.type, this.word);
  },

  methods: {
    getCount(type, word) {
      http
        .get("/boards/count", {
          params: {
            type: type,
            word: word,
          },
        })
        .then(({ data }) => {
          this.counts = data;
          this.maxPage = Math.floor(this.counts.boardCount / 50);
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },
    getBoardList(page) {
      http
        .get("/boards", {
          params: {
            type: this.type,
            word: this.word,
            page: page,
          },
        })
        .then(({ data }) => {
          this.boards = data;
          console.log(data);
        })
        .catch((error) => {
          console.error("GET 요청 에러 : ", error);
        });
    },

    searchBoardList() {
      this.page = 0;
      this.getCount(this.type, this.word);
      this.getBoardList(this.page);
    },

    moveDetail(item) {
      this.$router.push({ name: "boardDetail", params: { boardId: item.boardId } });
    },

    moveWrite() {
      this.$router.push({ name: "boardWrite" });
    },

    nextPage() {
      this.page++;
      this.getBoardList(this.page * 5);
    },

    prevPage() {
      this.page--;
      this.getBoardList(this.page * 5 + 4);
    },
  },
};
</script>

<style></style>
