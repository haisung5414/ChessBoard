import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	//주어진 위치에 기물을 놓고, 기물 정보 업데이트
	//증요! chessBoardStatus y x임. x y 가 아니라. 순서에 주의

	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	//해당 위치의 기물 객체 반환
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	// 해당 타일 핑크색으로.

	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	} //핑크색으로 마킹된 타일 원래 색으로 돌리기.
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	//표시할 메시지 setter
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
enum MagicType {MARK, CHECK, CHECKMATE};
	private int selectedX, selectedY; //PENDING 단계에서 골랐던 위치
	private boolean check, checkmate, end, isCheckmateChecked;

	enum ChessboardState { PENDING, EXECUTION };

	private ChessboardState currentState = ChessboardState.PENDING;
	private PlayerColor currentPlayer = PlayerColor.white;
	private Vector<Coordinate> markedCoordinate = new Vector<>();
	private Vector<Coordinate> avoidCheckedCoordinate = new Vector<>();
	private boolean doubleChecked = false;

	ListOfEveryPiece listOfEveryPiece = new ListOfEveryPiece();

	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {	// Only modify here
			// (x, y) is where the click event occured
			System.out.println(x + " " + y);
			loop(x,y);
		}
	}
	
	void onInitiateBoard(){
		//여기서 내가 설정한 다른 변수들을 초기화한다.
	}


	void loop(int x, int y){
		if (this.currentState == ChessboardState.PENDING) {
			selectedX = x;
			selectedY = y;
			processPendingLogic(x, y);
		} else {
			processExecutionLogic(x, y);
		}
	}
	void processPendingLogic(int x, int y){
		Piece selectedPiece = this.getIcon(x, y);
		//각 사람의 턴이라는 것
		//그 사람의 턴일 때 체크라는 것
		//누구의 턴이고 체크메이트라는 것
		//메세지로 나타내야한다.
		//체크메이트가 되면 그 상태에서 어떤 클릭을 해도 동작안한다.

		//체크메이트 검사하기
		if(check&&!isCheckmateChecked){// 체크이고, 체크메이트 검사를 하지 않은 경우
			Vector<Coordinate> possiblePosition = new Vector<>();
			Vector<Coordinate> myPieceList = listOfEveryPiece.getPieceList(currentPlayer);
			for(Coordinate myPiece : myPieceList){
				possiblePosition.addAll(getMovablePosition(myPiece.x, myPiece.y));
				//가능한 위치를 담은 벡터에, myPieceList의 원소 myPiece의 이동가능한 위치를 담는다.
				//나의 모든 기물들이 이동가능한 위치가 없으면, 그 때는 체크메이트이다. (혹은 스테일메이트)
			}

			if(myPieceList.isEmpty()){
				checkmate = true;
				if(currentPlayer == PlayerColor.white){
					setStatus("Checkmate! white WIN");
				}else{
					setStatus("Checkmate! black WIN");
				}
				/**
				 * 체크메이트!
				 * 여기서 끝을 내야 한다.
				 */
			}else{
				isCheckmateChecked = true;
				// 이 flag다시 false로 돌리는 시점은 state2에서 최종 선택시
			}
		}

		//모든 타일 원래 색깔로
		flushMarkedCoordinate();
		//현재 차례인 사람 표시
		showWhosTurn();

		//클릭 받아서 state2 execution으로 넘어가기
		if(selectedPiece.color == currentPlayer){
			this.currentState = ChessboardState.EXECUTION;
			//이동가능위치확인(); -> markedCoordinates에 넣자.
			markedCoordinate = getMovablePosition(x, y);
			//이동 가능경로 화면에 표시();
			markCoordinate(markedCoordinate);
		}
	}

	void processExecutionLogic(int x, int y){
		//currentPlayer는 반드시 black, white둘 중 하나니까 (none일 수 없으니)
		//삼항연산자로 opponentColor를 결정하게 해도 괜찮음.
		PlayerColor opponentColor = (currentPlayer == PlayerColor.black) ? PlayerColor.white : PlayerColor.black;

		Piece selectedPiece = getIcon(x, y);

		if(listOfEveryPiece.findPiece(markedCoordinate, new Coordinate(x, y)) != -1){//즉 클릭한 곳이 마킹된 곳이면
			//상대 기물 잡을 때와 빈 칸으로 움직일 때를 굳이 구분할 필요 없이 동일한 코드로 가능함.

			//전체 기물 리스트들 새로고침
			listOfEveryPiece.movePiece(currentPlayer, new Coordinate(selectedX, selectedY), new Coordinate(x, y));
			//내 기물 그곳으로 이동
			setIcon(x, y, this.getIcon(this.selectedX, this.selectedY));
			setIcon(this.selectedX, this.selectedY, new Piece());
			//턴 바꾸기
			this.currentPlayer = opponentColor;

			//체크 플래그 false로. 어짜피 체크를 없앨 수 있는 위치로밖에 못 움직인다.
			//만약 움직이지 못했다면 그대로 게임 끝남.
			check = false;

			//상대 킹 입장에서 체크인지 확인하기
			check = isKingCheck(findKing(opponentColor,chessBoardStatus), chessBoardStatus);

			// 아까 체크메이트 여부 검사했었음을 나타내는 플래그를 다시 false로
			isCheckmateChecked = false;
			//이동가능한 좌표 리스트 비우기
			markedCoordinate = new Vector<Coordinate>();
			//state 변경
			this.currentState = ChessboardState.PENDING;

		}else{ //클릭한 곳이 마킹되지 않은 곳
			this.currentState = ChessboardState.PENDING;
		}
	}

	//////////////////////보여지는 부분 메서드///////////////////////

	void markCoordinate(Vector<Coordinate> v){
		for (Coordinate c : v){
			markPosition(c.x, c.y);
		}
	}

	PlayerColor changePlayer(){
		if(this.currentPlayer == PlayerColor.black){
			return PlayerColor.white;
		}else if(this.currentPlayer == PlayerColor.white){
			return PlayerColor.black;
		}else{
			System.out.println("Error in changePlayer, currentPlayer is none");
			return null;
		}
	}

	/**
	 * 모든 하이라이트를 끈다.
	 */
	void flushMarkedCoordinate() {
		for(int i=0; i<8;i++){
			for(int j=0;j<8;j++){
				unmarkPosition(i,j);
			}
		}
	}

	/**
	 * 현재 차례인 사람 message로 표시하기. JLabel사용.
	 */
	void showWhosTurn(){
		if(currentPlayer == PlayerColor.white){
			setStatus("White's Turn");
		}else if(currentPlayer == PlayerColor.black){
			setStatus("Black's Turn");
		}
	}

	/**
	 * 어떤 기물이 이동가능한 위치를 알아내기.
	 * @param x
	 * @param y
	 * @return myPiece가 이동할 수 있는 곳의 Coordinate를 담은 Vector
	 */
	Vector<Coordinate> getMovablePosition(int x, int y){

		Vector<Coordinate> availableCoord = getAvailableCoordinates(x, y);
		Vector<Coordinate> certifiedCoord = getKingSafeCoordinates(availableCoord, new Coordinate(x,y), currentPlayer);

		return certifiedCoord;
	}//완성



	////////////////////각 기물별 움직임/////////////////////

	/**
	 * 기물이 움직일 때, 움직였을 때 왕이 체크되지 않는 좌표를 반환하는 함수
	 * @param availablePositions 해당 기물이 움직일 수 있는 좌표들 벡터
	 * @param movingPiece 움직이는 기물의 위치좌표
	 * @param color 확인하려는 플레이어의 색
	 * @return 왕이 체크되지 않는 좌표들의 벡터를 반환
	 */
	Vector<Coordinate> getKingSafeCoordinates(Vector<Coordinate> availablePositions, Coordinate movingPiece, PlayerColor color){

		//이거를 만드려면 대각선, 수직방향 검색 메서드를 만들어줘야함.
		Piece piece = getIcon(movingPiece.x, movingPiece.y);

		//가능한 이동 경로들에 대해 기물이 실제 이동한 경우를 저장하기위한 임시 체스보드
		Piece[][] tempChessBoard = new Piece[8][8];

		//움직일 수 있는 칸들을 저장하기 위해
		Vector<Coordinate> certifiedVector = new Vector<>();

		for(Coordinate pCoord : availablePositions){
			//tempChessBoard 에 current_ChessBoard 에서 movingPiece 삭제,
			//coord의 위치로 movingPiece 이동한 체스판을 대입한다.
			tempChessBoard = movePieceToNewPos(chessBoardStatus, movingPiece, pCoord);

			//currentPlayer가 항상 검사하고자 하는 그 킹이 맞는지 헷갈려서 그냥 찾고자 하는 king의 색을 파라미터로 받음.
			Coordinate kingCoord = findKing(color, tempChessBoard);

			//해당 temp를 가지고 아래의 검사를 시행.
			if(!isKingCheck(kingCoord, tempChessBoard)){
				certifiedVector.add(pCoord);
			}
		}
		return certifiedVector;
	}

	/**
	 * 어떤 기물을 주어진 좌표로 옮긴 상태의 체스판을 반환
	 * @param curChessBoard 현재 체스판의 기물상황
	 * @param targetPieceCoord 옮길 기물의 좌표
	 * @param moveTo 옮길 곳의 좌표
	 * @return
	 */
	Piece[][] movePieceToNewPos(Piece[][] curChessBoard, Coordinate targetPieceCoord, Coordinate moveTo) {
		Piece[][] changedChessBoard = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				changedChessBoard[i][j] = new Piece(curChessBoard[i][j].color, curChessBoard[i][j].type);
			}
		}
		//x,y순서 맞나 봐라.
		changedChessBoard[moveTo.y][moveTo.x] = new Piece(curChessBoard[targetPieceCoord.y][targetPieceCoord.x].color, curChessBoard[targetPieceCoord.y][targetPieceCoord.x].type);
		changedChessBoard[targetPieceCoord.y][targetPieceCoord.x] = new Piece();
		return changedChessBoard;
	}

	/**
	 * 킹이 체크당했는지 확인한다.
	 * @param kingCoord 킹의 위치
	 * @param curChessBoard 확인하려는 체스판의 기물상황
	 * @return 킹이 체크이면 true, 그렇지 않으면 false.
	 */
	boolean isKingCheck(Coordinate kingCoord, Piece[][] curChessBoard){
		/*
		킹을 중심으로
		대각선 각 방향으로 퀸, 비숍
		대각선 앞쪽 방향으로 폰
		나이트 이동 가능위치에 나이트
		직각 각 방향으로 퀸, 룩
		있는지 확인하고 하나라도 있다면 true
		없다면 false
		(죽 길게 있는 놈들의 경우 아래 함수를 재사용하고 마지막 원소를 쓰면 된다.)
		*/
		return false;
	}

	/**
	 * 킹의 위치를 찾아 반환한다.
	 * @param color 킹 색
	 * @param curChessBoard (Piece[][]) 찾으려는 체스보드
	 * @return 킹의 좌표
	 */
	Coordinate findKing(PlayerColor color, Piece[][] curChessBoard){
		Coordinate kingCoord = new Coordinate();

		return kingCoord;
	}

	Vector<Coordinate> getAvailableCoordinates(int x, int y){
		Vector<Coordinate> candidate = new Vector<>();
		Piece piece = getIcon(x, y);
		//들어온 좌표의 기물에 맞는 메서드를 불러서 쓰자.
		switch (piece.type) {
			case pawn -> candidate.addAll(getAvailablePawnCoordinates(x, y));
//			case knight -> candidate.addAll(getAvailableKnightCoordinate(piece.color, x, y));
//			case bishop -> candidate.addAll(getAvailableBishopCoordinate(piece.color, x, y));
//			case rook -> candidate.addAll(getAvailableRookCoordinate(piece.color, x, y));
//			case queen -> candidate.addAll(getAvailableQueenCoordinate(piece.color, x, y));
		}
		return candidate;
	}

	/**
	 * 폰이 이동할 수 있는 좌표의 리스트를 반환한다.
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 해당 폰이 이동할 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailablePawnCoordinates(int x, int y) {
		Vector<Coordinate> result = new Vector<>();
		PlayerColor pieceColor = getTargetColor(x,y);

		int offset = pieceColor == PlayerColor.black ? 1 : -1;
		PlayerColor oppositeColor = pieceColor == PlayerColor.black ? PlayerColor.white : PlayerColor.black;
		boolean initialState = pieceColor == PlayerColor.black ? x == 1 : x == 6;

		// 한 칸
		if (
				checkInBorder(x + offset, y)
						&& getTargetColor(x + offset, y) == PlayerColor.none
		) {
			result.add(new Coordinate(x + offset, y));
		}

		// 두 칸
		if (
				checkInBorder(x + offset * 2, y)
						&& initialState
						&& getTargetColor(x + offset * 2, y) == PlayerColor.none
		) {
			result.add(new Coordinate(x + offset * 2, y));
		}

		// 기물 잡을 때
		if (
				checkInBorder(x + offset, y + 1)
						&& getTargetColor(x + offset, y + 1) == oppositeColor
		) {
			result.add(new Coordinate(x + offset, y + 1));
		}

		// 기물 잡을 때
		if (
				checkInBorder(x + offset, y - 1)
						&& getTargetColor(x + offset, y - 1) == oppositeColor
		) {
			result.add(new Coordinate(x + offset, y - 1));
		}

		// 앙파상


		return result;
	}
//	Vector<Coordinate> getAvailableRookCoordinates(){}
//	Vector<Coordinate> getAvailableKnightCoordinates(){}
//	Vector<Coordinate> getAvailableBishopCoordinates(){}
//	Vector<Coordinate> getAvailableQueenCoordinates(){}
//	Vector<Coordinate> getAvailableKingCoordinates(){}

	/////////////////////세부 움직임 구현////////////////////////

	/**
	 * 해당 좌표가 체스판 위에 존재하는지 확인한다.
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 좌표가 체스판 위 위치한다면 true, 그렇지 않다면 false
	 */
	boolean checkInBorder(int x, int y) {
		return !(x >= 8 || x < 0 || y >= 8 || y < 0);
	}

	/**
	 * 좌표에 위치한 기물의 색을 반환한다.
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 해당 좌표에 위치한 기물 색
	 */
	//가져감
	PlayerColor getTargetColor(int x, int y) {
		return this.getIcon(x, y).color;
	}

	/**
	 * 대각선으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 대각선으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableDiagCoordinate(int x, int y) {
		Vector<Coordinate> result = new Vector<>();
		PlayerColor color = getTargetColor(x,y);

		result.addAll(getAvailableLeftUpperDiagCoordinate(color, x, y));
		result.addAll(getAvailableLeftLowerDiagCoordinate(color, x, y));
		result.addAll(getAvailableRightUpperDiagCoordinate(color, x, y));
		result.addAll(getAvailableRightLowerDiagCoordinate(color, x, y));

		return result;
	}

	/**
	 * 좌상단 대각선으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 좌상단 대각선으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableLeftUpperDiagCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x - 1;
		int cur_y = y - 1;

		while (cur_x >= 0 && cur_y >= 0) {
			if (!checkInBorder(cur_x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_x -= 1;
			cur_y -= 1;
		}

		return result;
	}

	/**
	 * 우상단 대각선으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 우상단 대각선으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableRightUpperDiagCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x - 1;
		int cur_y = y + 1;

		while (cur_x >= 0 && cur_y < 8) {
			if (!checkInBorder(cur_x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_x -= 1;
			cur_y += 1;
		}

		return result;
	}

	/**
	 * 좌하단 대각선으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 좌하단 대각선으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableLeftLowerDiagCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x + 1;
		int cur_y = y - 1;

		while (cur_x < 8 && cur_y >= 0) {
			if (!checkInBorder(cur_x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_x += 1;
			cur_y -= 1;
		}

		return result;
	}

	/**
	 * 우하단 대각선으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 우상단 대각선으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableRightLowerDiagCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x + 1;
		int cur_y = y + 1;

		while (cur_x < 8 && cur_y < 8) {
			if (!checkInBorder(cur_x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_x -= 1;
			cur_y -= 1;
		}

		return result;
	}

	/**
	 * 가로 세로로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 가로 세로로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableOrthogonalCoordinate(int x, int y) {
		Vector<Coordinate> result = new Vector<>();
		PlayerColor color = getTargetColor(x, y);

		result.addAll(getAvailableUpperCoordinate(color, x, y));
		result.addAll(getAvailableLowerCoordinate(color, x, y));
		result.addAll(getAvailableLeftCoordinate(color, x, y));
		result.addAll(getAvailableRightCoordinate(color, x, y));

		return result;
	}

	/**
	 * 우측으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 우측으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableRightCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_y = y + 1;

		while (cur_y < 8) {
			if (!checkInBorder(x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_y += 1;
		}

		return result;
	}

	/**
	 * 좌측으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 우측으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableLeftCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_y = y - 1;

		while (cur_y >= 0) {
			if (!checkInBorder(x, cur_y)) break;
			PlayerColor targetColor = getTargetColor(x, cur_y);

			if (targetColor == color) break;
			result.add(new Coordinate(x, cur_y));
			if (targetColor != PlayerColor.none) break;

			cur_y -= 1;
		}

		return result;
	}

	/**
	 * 상단으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 상단으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableUpperCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x - 1;

		while (cur_x >= 0) {
			if (!checkInBorder(cur_x, y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, y));
			if (targetColor != PlayerColor.none) break;

			cur_x -= 1;
		}

		return result;
	}

	/**
	 * 하단으로 움직일 수 있는 좌표의 리스트를 반환한다.
	 * @param color 기물 색
	 * @param x x 좌표
	 * @param y y 좌표
	 * @return 하단으로 움직일 수 있는 좌표 벡터
	 */
	Vector<Coordinate> getAvailableLowerCoordinate(PlayerColor color, int x, int y) {
		Vector<Coordinate> result = new Vector<>();

		int cur_x = x + 1;

		while (cur_x < 8) {
			if (!checkInBorder(cur_x, y)) break;
			PlayerColor targetColor = getTargetColor(cur_x, y);

			if (targetColor == color) break;
			result.add(new Coordinate(cur_x, y));
			if (targetColor != PlayerColor.none) break;

			cur_x += 1;
		}

		return result;
	}

}

class Coordinate {
	public final int x;
	public final int y;

	Coordinate(){
		x=0;
		y=0;
	}
	Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class ListOfEveryPiece {
	private Vector<Coordinate> blackPiece = new Vector<>();
	private Vector<Coordinate> whitePiece = new Vector<>();

	ListOfEveryPiece(){
		for(int i = 0; i<8 ; i++){
			for(int j=0; j<2 ; j++){
				Coordinate c = new Coordinate(j,i);
				blackPiece.add(c);
				// 이게 제대로 blackPiece들을 대표하는게 맞는가?
			}
			for(int j=6; j<8; j++){
				Coordinate c = new Coordinate(j,i);
				whitePiece.add(c);
			}
		}
	}
	//주어진 벡터에서 주어진 target좌표를 찾아서 그 인덱스 반환
	//없을 경우, -1을 반환
	public int findPiece(Vector<Coordinate> pieceList, Coordinate targetCoord){
		for(int i=0; i<pieceList.size(); i++){
			if(pieceList.get(i).x == targetCoord.x && pieceList.get(i).y == targetCoord.y){
				return i;
			}
		}
		return -1;
	}
	public void setBlackPiece(Vector<Coordinate> blackPiece) {
		this.blackPiece = blackPiece;
	}

	public void setWhitePiece(Vector<Coordinate> whitePiece) {
		this.whitePiece = whitePiece;
	}

	//각각의 색깔의 전체 기물 리스트에서 기물 이동시
	//기존 위치 좌표 삭제하고, 그 자리에 새로운 좌표 저장.
	public void moveBlackPiece(Coordinate from, Coordinate to){
		int indexOfFrom = findPiece(blackPiece, from);
		this.blackPiece.remove(indexOfFrom);
		removePiece(to);
		this.blackPiece.add(indexOfFrom,to);
	}
	public void moveWhitePiece(Coordinate from, Coordinate to){
		int indexOfFrom = findPiece(whitePiece, from);
		this.whitePiece.remove(indexOfFrom);
		//전체에서 to라는 좌표를 삭제하고(반대편에 있을 경우를 위해)
		removePiece(to);
		this.whitePiece.add(indexOfFrom, to); //다시 whitePiece 벡터에 더해준다.
	}
	public void movePiece(PlayerColor currentPlayer, Coordinate from, Coordinate to){
		//해당되는 기존 좌표값 삭제
		//새로운 좌표값 추가
		//옮긴 곳에 상대편 기물 있을 때 그 좌표도 리스트에서 삭제
		if(currentPlayer == PlayerColor.white){
			moveWhitePiece(from, to);
		}else if(currentPlayer == PlayerColor.black){
			moveBlackPiece(from, to);
		}
	}

	//백, 흑 전체 기물 리스트 중 입력받은 좌표의 삭제
	public void removePiece(Coordinate target){
		int i = findPiece(this.blackPiece, target);
		int j = findPiece(this.whitePiece, target);
		if(i!=-1){
			this.blackPiece.remove(i);
		}else if(j!=-1){
			this.whitePiece.remove(j);
		}
	}

	//깊은 복사 해서 준다.
	//주소 다른, 내용물 같은 벡터 객체
	public Vector<Coordinate> getPieceList(PlayerColor currentPlayer) {
		Vector<Coordinate> temp = new Vector<>();
		if(currentPlayer == PlayerColor.white){
			for(Coordinate c : this.whitePiece){
				temp.add(c);
			}
			return temp;
		}else if(currentPlayer == PlayerColor.black){
			for(Coordinate c : this.blackPiece){
				temp.add(c);
			}
			return temp;
		}else{
			return null;
		}
	}
}
