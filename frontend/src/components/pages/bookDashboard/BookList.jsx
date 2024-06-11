import { useState } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../../../services/AuthContext";
import "../../../styles/BookList.css";
import axios from "axios";
// import SearchBar from '../SearchBar';
// import Paging from '../Paging';

const BookList = ({
  books,
//   booksPage,
//   updateBooks,
//   reachedMaxPage,
//   booksDisplayStr,
//   booksDisplay,
}) => {
  const { token } = useAuth();

  // State to manage the confirmation modal
  const [showModal, setShowModal] = useState(false);
  const [bookIdToDelete, setBookIdToDelete] = useState(null);

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/books/${bookIdToDelete}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } catch (error) {
      console.error("Error:", error);
    }
    setShowModal(false);
  };

  // Function to export books to CSV
  //   const exportToCSV = () => {
  //     const csvContent = "data:text/csv;charset=utf-8," +
  //       books.map(book => `${book.id},${book.name},${book.description},${book.status},${book.totalTasks},${book.completedTasks}`).join("\n");
  //     const encodedUri = encodeURI(csvContent);
  //     const link = document.createElement("a");
  //     link.setAttribute("href", encodedUri);
  //     link.setAttribute("download", "books.csv");
  //     document.body.appendChild(link);
  //     link.click();
  //   };

  //   const calculateTasks = (bookId) => {
  //     const book = books.find(book => book.id === bookId);
  //     if (book) {
  //       const totalTasks = book.tasks.length;
  //       const incompletedTasks = book.tasks.filter(task => task.status === "TODO" || task.status === "IN_PROGRESS").length;
  //       const completedTasks = totalTasks - incompletedTasks ; // Calculate completed tasks
  //       const percentageCompleted = totalTasks === 0 ? 0 : (completedTasks / totalTasks) * 100; // Calculate percentage
  //       return { incompletedTasks, totalTasks, percentageCompleted };
  //     }
  //     return { totalTasks: 0, incompletedTasks: 0, percentageCompleted: 0 };
  //   };

  //   // Function to rename status values
  //   const renameStatus = (originalStatus) => {
  //     // Define mapping of original status values to renamed values
  //     const statusMap = {
  //       'TODO': 'To Do',
  //       'IN_PROGRESS': 'In Progress',
  //       'COMPLETED': 'Completed',
  //       // Add more mappings as needed
  //     };

  //     // Return renamed status value if mapping exists, otherwise return the original status value
  //     return statusMap[originalStatus] || originalStatus;
  //   };

    const truncateDescription = (description) => {
      if (description.length > 200) {
        return `${description.substring(0, 200)}...`;
      }
      return description;
    };

  //   const getProgressColor = (incompletedTasks, totalTasks) => {
  //     if (totalTasks === 0 && incompletedTasks === 0) {
  //       return '#cec7c7';
  //     } else {
  //     const progressPercentage = ((totalTasks-incompletedTasks) / totalTasks) * 100;
  //     if (progressPercentage === 0) {return '#E61111';
  //     } else if (progressPercentage < 25) {
  //       return '#E61111'; // Red color for less than 25% progress
  //     } else if (progressPercentage < 50) {
  //       return '#F3572B'; // Orange color for less than 50% progress
  //     } else if (progressPercentage < 75) {
  //       return '#FDD346'; // Yellow color for less than 75% progress
  //     } else {
  //       return '#79C343'; // Green color for 75% or more progress
  //     }
  //   }
  //   };

  return (
    <div className="books-container">
      <div className="books-header">
        <div className="align">
          <h1 className="books-title">books</h1>
          {/* <SearchBar /> */}

          {/* <select
            value={booksDisplayStr}
            onChange={(e) => booksDisplay(e.target.value)}
            className="books-select"
          >
            <option value="ALL">Show All</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="COMPLETED">Completed</option>
          </select> */}
        </div>
        <div className="button-group">
          <Link to="/create">
            <button className="new-book-btn">+ New book</button>
          </Link>
        </div>
      </div>
      {/* <Paging
        pageNum={booksPage}
        update={updatebooks}
        reachedMaxPage={reachedMaxPage}
      /> */}
      <div className="book-list">
        {books.map((book) => {
          return (
            <div className="book-preview" key={book.id}>
              <Link to={`/books/${book.id}`}>
                <h3>{book.name}</h3>
                <div className="description">
                  {truncateDescription(book.description)}
                </div>
              </Link>
            </div>
          );
        })}
      </div>
      {/* <Link to="/create">
        <button className="new-book-btn">+ New book</button>
      </Link> */}
      {/* Confirmation modal */}
      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <p>Are you sure you want to delete this book?</p>
            <div>
              <button onClick={handleDelete}>Yes</button>
              <button onClick={() => setShowModal(false)}>No</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default BookList;
