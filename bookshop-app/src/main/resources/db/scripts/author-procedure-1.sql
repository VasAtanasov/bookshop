CREATE PROCEDURE udp_get_author_books_count(first_name VARCHAR(50), last_name VARCHAR(50))
BEGIN
    SELECT a.first_name as firstName,
           a.last_name as lastName,
           COUNT(b.id) as booksCount
    FROM bookshop_db.authors AS a
             LEFT JOIN
         books AS b ON b.author_id = a.id
    WHERE a.first_name = first_name
      AND a.last_name = last_name
    GROUP BY a.first_name, a.last_name;
END