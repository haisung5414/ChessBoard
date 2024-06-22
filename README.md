[github연결](https://brunch.co.kr/@mystoryg/168)


## 계획

``` java

state 1-pending{
//방금 턴 바뀐 경우, 체크메이트 확인
	if(is_Check&&!is_Checkmate_Checked){체크인 경우
		Vector possible_positions;
		for(자신의 모든 기물 piece){
			possible_positions.뒤에달기(이동가능위치확인(piece));
		}
		if(possible_positions.isEmpty()){
			체크메이트! 끝.
		}else{is_Checkmate_Checked = 1;}
		이 flag다시 0으로 돌리는 시점은 state2에서 최종 선택한 경우.
	}
	
	
	모든 타일 원래 색깔로(){
		for(int i=0; i<8;i++){
			for(int j=0;j<8;j++){
				unmarkPosition(i,j);
			}
		}
	}

	현재 차례인 사람 표시(){
		//앞서 state2에서 다음 차례로 넘어갈 때 색깔을 바꿨으므로, 여기선 그대로 읽으면 된다.
		//즉 currentPlayer에 저장된 값이 현재 차례의 사람인 것.
		if(currentPlayer == PlayerColor.white){
			setStatus("White's Turn");
		}else if(currentPlayer == PlayerColor.black){
			setStatus("Black's Turn");
		}
	}
	
	/*
		if(클릭한게 빈칸()||클릭한 팀()==상대팀){
			아무 일도 일어나지 않는다. 그냥 끝.
			그러면 새로운 입력이 알아서 들어올 것.
			==> 이건 아예 구현할 필요조차 없네 그러면.
	*/
	
	//클릭 받아서 state2로 넘어가기
	if(클릭한 팀 == 우리팀){
		Vector 이동가능위치확인(Piece my_piece){
		Vector available_positions;
		Vector certified_positions;
	
		switch(piece.type) {
			case pawn :
				잡을 수 있는 놈이 대각선 앞에 있는 경우();
				앙파상 고려하기();
				처음_2칸전진();
			case rook :
				수직 방향 검토();
			case bishop :
				대각선 방향 검토();
			case knight :
				offset사용, 현위치 기준 가능한 곳();
			case queen :
				수직방향();
				대각선 방향();
			case king :	
				대각선 방향 한 칸씩
		} 각 기물별로 이동가능한 위치. 벽에 막히거나, 같은 팀, 다른 팀 기물에 막힘
	
		certified_positions = king_Safe_Vectors(available_positions);
		return certified_positions;
		}
		
		void 받은경로_화면에표시(Vector possible_positions){
		}
	}
} //여기까지 state1
//여기까지 했다 0622 1451

void 말_이동하기(클릭받아서){
	if(해당위치에 상대 기물 존재){
		//이 경우 그 기물을 잡고 상대 리스트에서 기물을 삭제하고, 턴을 넘기고, state1으로 바꾸어야 한다.
		상대 기물 리스트에서 잡은 기물 삭제;
		내 기물 그곳으로 이동;
		currentPlayer전환;
		상대 킹 입장에서 체크인지 확인하기; //iskingCheck()활용
		isCheckmateChecked = false;

		이동가능한 좌표 리스트 비우기
	}else if(해당 위치가 이동가능한 칸이 아님){
		state1 처음으로 이동;
		즉, 그냥 state1만 설정하면 됨.
	}else {
		내 기물 그곳으로 이동;
		내 기물 리스트 업데이트;
		턴 넘기기;
		state1으로;
		isCheckmateChecked = false;
	}
}
```

``` java
기타 세부적인 메서드들.

Vector king_Safe_Vectors(current_ChessBoard, 주소 pivot, Vector possibleCoord){
	Piece piece = pivot;
	체스판 tempChessBoard;
	Vector certified_Vector;
	Coordinates kingCoord = 어떻게든 구해서 넣어놔라;

	for(coordinates coord in possibleCoord){
		tempChessBoard 에 current_ChessBoard 에서 pivot 삭제, 
		coord의 위치로 pivot 이동한 체스판을 대입한다.

		해당 temp를 가지고 아래의 검사를 시행.

		if(!isKingCheck(kingCoord, tempChessBoard)){
			certified_Vector.append(coord);
		}
		킹을 중심으로
		대각선 각 방향으로 퀸, 비숍
		대각선 앞쪽 방향으로 폰
		나이트 이동 가능위치에 나이트
		직각 각 방향으로 퀸, 룩 
		있는지 확인하고 하나라도 있다면 그냥 지나가고
		없다면 
		certified_Vector에 넣어.
		(죽 길게 있는 놈들의 경우 아래 함수를 재사용하고 마지막 원소를 쓰면 된다.)
	}
	return certified_Vector;
}

getPawnPossibleMoves(){

}
등등 그 외의 기물마다의 움직임.

orthogonal_Check(){
}
Vector orthogonal_Check_NE(current_ChessBoard,주소 pivot,int distance){
	주어진 current_ChessBoard에서
	pivot을 중심으로
	distance만큼의 칸 만큼을 검사해서 (북동 방향으로)
	이동 가능한 칸들을 각각 좌표의 형태로 벡터의 원소로 해서 반환한다.
		이때 체스 판 끝에 다다랐을 때 : 거기까지
		본인 기물과 마주쳤을 때 : 거기 전 칸까지
		상대 기물과 마주쳤을 때 : 그 칸까지
}
동일하게 NW, SW, SE 존재.


diagonal_Check(){
}

boolean isKingCheck(Coordinates king_coord, ChessBoard currentChessboard){
	킹을 중심으로
	대각선 각 방향으로 퀸, 비숍
	대각선 앞쪽 방향으로 폰
	나이트 이동 가능위치에 나이트
	직각 각 방향으로 퀸, 룩 
	있는지 확인하고 하나라도 있다면 return false;
	(죽 길게 있는 놈들의 경우 아래 함수를 재사용하고 마지막 원소를 쓰면 된다.)

}
```
