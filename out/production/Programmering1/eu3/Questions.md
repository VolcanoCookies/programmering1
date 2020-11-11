## 2. The pieces can be stored in a single vector, even if their types differ, why?
This is because all pieces are instances of the same abstract class `Chesspiece`, thus a vector storing chesspieces
 can store all different kinds of chesspieces. If we were to make a vector of say `Rook`s then we would not be able
  to store different types of pieces in that vector, only rooks.
## 3. Even through their behavior differs, the pieces can be presented in the same loop with each other, why?
This is once again because they extend the same abstract class `Chesspiece`, thus if we refer to them as chesspieces
 then we can call all methods defined in the class chesspieces, even if the methods logic is overwriten in the
  separate piece classes, as long as the provided and resulting variables of the method stay the same. When refering
   to a piece as a `Chesspiece` we cannot however call methods solely defined in the individual chesspiece classes.