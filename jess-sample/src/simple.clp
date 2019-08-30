; 例子
(import com.rsoft.jess.*)
(deftemplate Order       (declare (from-class Order)))

(defrule 10%-volume-discount
    "Give a 10% discount to everybody who spends more than $100."
    ?o <- (Order {amount > 200})
    =>
    (add (new Order "10% volume discount" (/ ?o.amount 10))))