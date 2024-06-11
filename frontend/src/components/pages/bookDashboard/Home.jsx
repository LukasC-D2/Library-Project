import { useState } from 'react';
import BookList from "./BookList";
import useFetch from "../../../services/useFetch";
import { useAuth } from '../../../services/AuthContext';

const Home = () => {
  const { token } = useAuth(); // Access token from AuthContext
  const [booksPage, setBooksPage] = useState(1);
  const [showBooks, setShowBooks] = useState("ALL");

  function getBooks() {
    return useFetch(`http://localhost:8080/api/books?page=${booksPage}${showBooks == 'ALL' ? '' : `&show=${showBooks}`}`, token);
  }

  const { error, isPending, resCode, data: books} = getBooks();

  const handlePageSwitch = async (i) => {
    setBooksPage(booksPage + i);
  }

  const handleShowBooksChange = async (str) => {
    setShowBooks(str);
  }

  return (
    <div className="home">
      { error && <div>{ error }</div> }
      { isPending && <div>Loading...</div> }
      { books && <BookList books={books} booksPage={booksPage} updateBooks={handlePageSwitch} reachedMaxPage={resCode == 202} booksDisplayStr={showBooks} booksDisplay={handleShowBooksChange} /> }
    </div>
  );
}

export default Home;
