# 概要
非推奨。ViewTypeの分け方が良くないので、ClickListenerをつけるときに面倒
推奨する方法(https://github.com/eatbaguette/RecyclerViewSection)

スクロールする画面は基本的にRecyclerViewで作る
複数のまとまりを持つ複雑な画面はまとまりをSection毎に分ける

# 詳細
SectionGroup: それぞれのSectionを管理する  
HeaderSection: ヘッダーを持つSection  
NoHeaderSection: ヘッダーを持たないSection  
