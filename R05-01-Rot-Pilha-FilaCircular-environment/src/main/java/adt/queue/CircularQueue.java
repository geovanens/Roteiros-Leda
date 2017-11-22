package adt.queue;

public class CircularQueue<T> implements Queue<T> {

   private T[] array;
   private int tail;
   private int head;
   private int elements;

   public CircularQueue(int size) {
      array = (T[]) new Object[size];
      head = -1;
      tail = -1;
      elements = 0;
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (element != null) {

         if (isFull()) {
            throw new QueueOverflowException();
         } else if (isEmpty()) {
            this.head = 0;
            this.tail = 0;
            this.array[0] = element;

         }

         else {
            this.tail = this.array.length - 1 - this.elements;
            this.array[this.tail] = element;
         }

         this.elements++;

      }
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }

   @Override
   public T head() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }

   @Override
   public boolean isEmpty() {
      return this.head == -1 && this.tail == -1;
   }

   @Override
   public boolean isFull() {
      return this.elements == this.array.length - 1;
   }

}
