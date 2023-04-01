package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.Main.main
import org.scalatest.funsuite.AnyFunSuite

class ExtraSolverTest extends AnyFunSuite {
  test("5cd_4s5hTsQh9h"){
    assert(
      "4s5hTsQh9h Qc8d7cTcJd 5s5d7s4dQd 7h6h7d2cJc 3cKs4cKdJs 2hAhKh4hKc As6d5cQsAc" ==
      main("five-card-draw 4s5hTsQh9h Qc8d7cTcJd 5s5d7s4dQd 3cKs4cKdJs 2hAhKh4hKc 7h6h7d2cJc As6d5cQsAc"),
    )
  }

  test("5cd_7h4s4h8c9h"){
    assert(
      "4c8h2h6c9c Ah9d6s2cKh Kd9sAs3cQs 7h4s4h8c9h Tc5h6dAc5c" ==
      main("five-card-draw 7h4s4h8c9h Tc5h6dAc5c Kd9sAs3cQs Ah9d6s2cKh 4c8h2h6c9c"),
    )
  }

  test("5cd_5s3s4c2h9d"){
    assert(
      "5s3s4c2h9d 4h6s8hJd5d 5c3cQdTd9s 8dKsTc6c2c 8c3d7h7dTs KhJs9c5h9h AhQhKcQc2d" ==
      main("five-card-draw 5s3s4c2h9d 8dKsTc6c2c 4h6s8hJd5d 5c3cQdTd9s AhQhKcQc2d KhJs9c5h9h 8c3d7h7dTs"),
    )
  }

  test("th_5c6dAcAsQs"){
    assert(
      "2cJc Kh4h=Ks4c Kc7h KdJs 6h7d 2hAh" ==
      main("texas-holdem 5c6dAcAsQs Ks4c KdJs 2hAh Kh4h Kc7h 6h7d 2cJc"),
    )
  }

  test("th_2h5c8sAsKc"){
    assert(
      "Jc6s Qs9h 3cKh KdQh" ==
      main("texas-holdem 2h5c8sAsKc Qs9h KdQh 3cKh Jc6s"),
    )
  }

  test("th_3d4s5dJsQd"){
    assert(
      "9h7h 2dTc KcAs 7sJd TsJc Qh8c 5c4h" ==
      main("texas-holdem 3d4s5dJsQd 5c4h 7sJd KcAs 9h7h 2dTc Qh8c TsJc"),
    )
  }

  test("oh_5c6dAcAsQs"){
    assert(
      "8d7cTcJd 6h7d2cJc Qd3cKs4c Kh4hKc7h KdJs2hAh 5s5d7s4d TsQh9hQc" ==
      main("omaha-holdem 5c6dAcAsQs TsQh9hQc 8d7cTcJd 5s5d7s4d Qd3cKs4c KdJs2hAh Kh4hKc7h 6h7d2cJc"),
    )
  }

  test("oh_3d4s5dJsQd"){
    assert(
      "9h7h2dTc 7cThKs5s 7sJdKcAs 8d9s5c4h 5hJh2s7d Qh8cTsJc 8s2h6s8h" ==
      main("omaha-holdem 3d4s5dJsQd 8s2h6s8h 7cThKs5s 5hJh2s7d 8d9s5c4h 7sJdKcAs 9h7h2dTc Qh8cTsJc"),
    )
  }

  test("oh_3d3s4d6hJc"){
    assert(
      "Qc8dAd6c KsAsTcTs Js2dKd8c 7dQsAc5d Jh2h3c9c" ==
      main("omaha-holdem 3d3s4d6hJc Js2dKd8c KsAsTcTs Jh2h3c9c Qc8dAd6c 7dQsAc5d"),
    )
  }

  test("draw_all"){
    assert(
      "2d4h3d7h5d=7s3d2c4s5d Ah9hQdJhKd=JcAdQs9cKc 2d4h3d2h5d=2s3d2c4s5d 2dAhKd2hQd=2sAd2cQsKd 2d4h3dAhAd=AsAd2c4s3d AsAdQcKsJd=QdKhAdJhAh 2d4h3d2h3d=2s3d2c4s3d 2dAh3d2h3d=2s3d2cAs3d AdAhKdKh2d=AsKdAc2sKc AdAhKdKhQd=AsKdAcQsKc 2c2d3s2h4c=2c2h4d2h3d 2c2dAs2hKc=2c2hKd2hAd AcAd2sAh3c=AcAh2dAh3d AcAdKsAhQc=AcAhKdAhQd 2c4d3cAd5d=Ac4d3c5d2d 2c4d3c6d5d=6c4d3c5d2d AcJdTcKdQd=TcJdKcAdQd 2c4c3c7c5c=7s3s2s4s5s Ah9hQhJhKh=JdAdQd9dKd 2s2c2d3s3c 2sAh2h2sAc 3s3c2d3d2c 3s3dAh3cAc KsAdAcAsKc 2h2s2c2d3s 2h2sAh2d2c 3s3d2d3c3h 3sAs3d3c3h AhAsAdAc2c AsAdKdAcAh 5d2d4d3dAd=5s2s4sAs3s 2d4d3d5d6d=6s4s5s3s2s AdTdJdQdKd=QsKsJsAsTs" ==
      main("five-card-draw 5s2s4sAs3s QsKsJsAsTs 6s4s5s3s2s 5d2d4d3dAd 2d4d3d5d6d AdTdJdQdKd 2h2s2c2d3s 2h2sAh2d2c 3s3d2d3c3h 3sAs3d3c3h AhAsAdAc2c AsAdKdAcAh 2s2c2d3s3c 2sAh2h2sAc 3s3c2d3d2c 3s3dAh3cAc KsAdAcAsKc 7s3s2s4s5s 2c4c3c7c5c JdAdQd9dKd Ah9hQhJhKh 2c4d3cAd5d Ac4d3c5d2d 2c4d3c6d5d 6c4d3c5d2d TcJdKcAdQd AcJdTcKdQd 2c2d3s2h4c 2c2h4d2h3d 2c2dAs2hKc 2c2hKd2hAd AcAd2sAh3c AcAh2dAh3d AcAdKsAhQc AcAhKdAhQd 2s3d2c4s3d 2d4h3d2h3d 2s3d2cAs3d 2dAh3d2h3d AsKdAc2sKc AdAhKdKh2d AsKdAcQsKc AdAhKdKhQd 2s3d2c4s5d 2d4h3d2h5d 2sAd2cQsKd 2dAhKd2hQd AsAd2c4s3d 2d4h3dAhAd QdKhAdJhAh AsAdQcKsJd 7s3d2c4s5d 2d4h3d7h5d JcAdQs9cKc Ah9hQdJhKd"),
    )
  }
}
